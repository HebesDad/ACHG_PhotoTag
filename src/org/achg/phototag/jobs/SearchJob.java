package org.achg.phototag.jobs;

import java.util.ArrayList;
import java.util.List;

import org.achg.phototag.generated.model.PhotoTagModel.Folder;
import org.achg.phototag.generated.model.PhotoTagModel.Image;
import org.achg.phototag.generated.model.PhotoTagModel.TagValue;
import org.achg.phototag.model.IFolderVisitor;
import org.achg.phototag.model.ModelManager;
import org.achg.phototag.search.SearchCriteriaContainer;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

public class SearchJob extends Job {

	public SearchJob() {
		super("Search");
	}

	public boolean belongsTo(Object obj) {
		return this.getClass() == obj;
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		
		if (SearchCriteriaContainer.getInstance().getCriteria().length==0)
		{
			SearchCriteriaContainer.getInstance().setResults(null);
			return Status.OK_STATUS;
		}
		
		List<Image> images = new ArrayList<>();

		ModelManager.getInstance().visitFolders(new FolderVisitor(images));

		SearchCriteriaContainer.getInstance().setResults(images);
		return Status.OK_STATUS;
	}

	private class FolderVisitor implements IFolderVisitor {
		List<Image> _images;

		public FolderVisitor(List<Image> list) {
			_images = list;
		}

		@Override
		public boolean visitFolder(Folder folder) {
			Image[] images = folder.getImages();
			if (images != null) {
				for (Image contender : images) {
					if (imageMatches(contender)) {
						_images.add(contender);
					}
				}
			}
			return true;
		}

		private boolean imageMatches(Image contender) {
			for (TagValue criteria : SearchCriteriaContainer.getInstance().getCriteria()) {
				boolean found = criteria.getValue().equals("<unset>");
				for (TagValue actual : contender.getTagValues()) {
					// think about unset
					if (actual.getTag() == criteria.getTag()) {
						if (actual.getValue().equals(criteria.getValue()))
							found = true;
						if (criteria.getValue().equals("<unset>"))
							return false;

						// todo consider subtags - includes <any>
						if (found && criteria.getSubTag() != null) {
							found = criteria.getSubValue().equals("<unset>");
							if (criteria.getSubTag() == actual.getSubTag()) {
								if (criteria.getSubValue().equals(actual.getSubValue())) {
									found = true;
								}
								if (criteria.getSubValue().equals("<unset>"))
									return false;
							}
							if (criteria.getSubValue().equals("<any>")) {
								found = true;
							}

						}
					}
				}

				if (!found)
					return false;
			}
			return true;
		}
	}
}

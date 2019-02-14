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

/**
 * Job for searching by tag value
 * 
 * @author Patrick
 *
 */
public class SearchJob extends Job
{
	private static final String UNSET_TAG_VALUE = "<unset>";
	private static final String ANY_TAG_VALUE = "<any>";

	/**
	 * Constructor
	 */
	public SearchJob()
	{
		super("Search");
	}

	@Override
	public boolean belongsTo(Object obj)
	{
		return this.getClass() == obj;
	}

	@Override
	protected IStatus run(IProgressMonitor monitor)
	{
		SearchCriteriaContainer container = SearchCriteriaContainer.getInstance();
		if(container.getCriteria().length == 0)
		{
			container.setResults(null);
			return Status.OK_STATUS;
		}

		List<Image> images = new ArrayList<>();

		ModelManager.getInstance().visitFolders(new FolderVisitor(images));

		container.setResults(images);
		return Status.OK_STATUS;
	}

	private class FolderVisitor implements IFolderVisitor
	{
		List<Image> _images;

		public FolderVisitor(List<Image> list)
		{
			_images = list;
		}

		@Override
		public boolean visitFolder(Folder folder)
		{
			Image[] images = folder.getImages();
			if(images != null)
			{
				for(Image contender : images)
				{
					if(imageMatches(contender))
					{
						_images.add(contender);
					}
				}
			}
			return true;
		}

		private boolean imageMatches(Image contender)
		{
			for(TagValue criteria : SearchCriteriaContainer.getInstance().getCriteria())
			{
				boolean found = criteria.getValue().equals(UNSET_TAG_VALUE);
				for(TagValue actual : contender.getTagValues())
				{
					// think about unset
					if(actual.getTag() == criteria.getTag())
					{
						if(actual.getValue().equals(criteria.getValue()))
						{
							found = true;
						}
						if(criteria.getValue().equals(UNSET_TAG_VALUE))
						{
							return false;
						}

						// todo consider subtags - includes <any>
						if(found && criteria.getSubTag() != null)
						{
							found = criteria.getSubValue().equals(UNSET_TAG_VALUE);
							if(criteria.getSubTag() == actual.getSubTag())
							{
								if(criteria.getSubValue().equals(actual.getSubValue()))
								{
									found = true;
								}
								if(criteria.getSubValue().equals(UNSET_TAG_VALUE))
								{
									return false;
								}
							}
							if(criteria.getSubValue().equals(ANY_TAG_VALUE))
							{
								found = true;
							}
						}
					}
				}

				if(!found)
				{
					return false;
				}
			}
			return true;
		}
	}
}

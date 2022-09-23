package com.mysite.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.inject.Inject;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class VariationModel {

	@Inject
	private String targetExperience;

	@Inject
	private String fragmentVariationPath;

	public String getTargetExperience() {
		return targetExperience;
	}

	public String getFragmentVariationPath() {
		return fragmentVariationPath;
	}
}

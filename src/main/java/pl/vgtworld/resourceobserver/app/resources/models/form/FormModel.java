package pl.vgtworld.resourceobserver.app.resources.models.form;

import pl.vgtworld.resourceobserver.app.resources.dto.ResourceFormDto;

import java.util.List;

public class FormModel {

	private List<String> errors;

	private ResourceFormDto resource;

	private Integer resourceId;

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public ResourceFormDto getResource() {
		return resource;
	}

	public void setResource(ResourceFormDto resource) {
		this.resource = resource;
	}

	public Integer getResourceId() {
		return resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

}

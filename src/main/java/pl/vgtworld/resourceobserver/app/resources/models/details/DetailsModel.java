package pl.vgtworld.resourceobserver.app.resources.models.details;

import pl.vgtworld.resourceobserver.storage.resource.Resource;
import pl.vgtworld.resourceobserver.storage.scan.Scan;

import java.util.List;

public class DetailsModel {

	private Resource resource;

	private List<Scan> newestScans;

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public List<Scan> getNewestScans() {
		return newestScans;
	}

	public void setNewestScans(List<Scan> newestScans) {
		this.newestScans = newestScans;
	}

}

package resources;
//enum is a special class in java which has collection of constants or methods

public enum APIResources {

	AddPlaceAPI("/maps/api/place/add/json"), getPlaceAPI("/maps/api/place/get/json"), deletePlaceAPI("/maps/api/place/delte/json");

	private String resource;

	private APIResources(String resource) { //setter - constructor takes resource from stepDef and assigns to local varResource
		this.resource = resource;

	}

	public String getResource() {  //getter - method returns localvarResource
		return resource;
	}
}

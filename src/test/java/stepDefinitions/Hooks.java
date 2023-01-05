package stepDefinitions;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void before() throws Exception {
		// execute this code only when place_id is NULL
		// write a code that gives palce_id

		StepDefinition obj = new StepDefinition();
		if (StepDefinition.place_id == null) {

			obj.add_place_payload_with("Hyd", "English", "Whitefields house");
			obj.user_calls_with_post_http_request("AddPlaceAPI", "POST");
			obj.verify_created_place_id_maps_to_using("Whitefields house", "getPlaceAPI");
		} 
	}

}

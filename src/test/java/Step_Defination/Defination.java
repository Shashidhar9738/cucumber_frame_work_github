package Step_Defination;

import java.io.IOException;
import org.testng.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utility.payloadconvertor;

public class Defination {

	public static String BaseURL = "https://api.github.com";
	public static String create_repo_payload;
	public static String create_issue_payload;
	// public static String createorderspayload;
	public static int id;
	public static String Name;
//	public static String string2="Shashidhar9738";
//	public static String string4=Name;

	RequestSpecification requestSpecification;
	Response response;
	JsonPath jsonpath;
	public static String token;
	public final String Authorization = "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";

	@Given("login to github")
	public void login_to_github() throws IOException {
		create_repo_payload = payloadconvertor.generatepayload("create_repo.json");
		//System.out.println(create_repo_payload);

	}

	@When("create a repo {string} storing the repo name")
	public void create_a_repo_storing_the_repo_name(String string) throws IOException {

		requestSpecification = RestAssured.given().header("Authorization", "Bearer " + Authorization);
		requestSpecification.contentType(ContentType.JSON);
		requestSpecification.body(create_repo_payload);
		response = requestSpecification.post(BaseURL + string);
		jsonpath = new JsonPath(response.getBody().asString());
		Name = jsonpath.get("name");
		System.out.println("full_name = " + Name);

	}

	@Then("verify the status code {int}")
	public void verify_the_status_code(Integer int1) {
		int1 = response.getStatusCode();
		Assert.assertEquals(int1, response.getStatusCode());
		System.out.println("status code is " + int1);

	}

	@Given("creating an issue with json data")
	public void creating_an_issue_with_json_data() throws IOException {

		create_issue_payload = payloadconvertor.generatepayload("create_issue.json");
		//System.out.println(create_issue_payload);

	}

	@When("creating a issue {string} saving owner name and repo name")
	public void creating_a_issue_saving_owner_name_and_repo_name(String string1) {

		requestSpecification = RestAssured.given().header("Authorization", "Bearer " + Authorization);
		requestSpecification.contentType(ContentType.JSON);
		requestSpecification.body(create_issue_payload);
		response = requestSpecification.post(BaseURL + string1 +"Shashidhar9738/"+ Name);
		jsonpath = new JsonPath(response.getBody().asString());
		Name = jsonpath.get("full_name");
		//System.out.println("orderId = "+ID);
		System.out.println("full_name = " + Name);
		jsonpath = new JsonPath(response.getBody().asString());
		id = jsonpath.get("id");
		System.out.println("orderId = "+id);
//		String responseBody = response.getBody().asString();
//		System.out.println(responseBody);

	}

	@Then("Verify the Status code is {int}")
	public void verify_the_status_code_is(Integer int2) {
		Assert.assertEquals(int2, response.getStatusCode());
		int2 = response.getStatusCode();
		System.out.println("status code is " + int2);
	}
	@Given("deleting the repo")
	public void deleting_the_repo() {
		System.out.println(Name);
	}

	@When("deleteling a repo {string} saving owner name and repo name")
	public void deleteling_a_repo_saving_owner_name_and_repo_name(String string2) {
		requestSpecification = RestAssured.given().header("Authorization", "Bearer " + Authorization);
		requestSpecification.contentType(ContentType.JSON);
		//requestSpecification.body(create_issue_payload);
		response = requestSpecification.delete(BaseURL + string2+ Name);
		System.out.println(BaseURL + string2 + Name);
	
	}

	@Then("Verify the Status code for deleting the repo {int}")
	public void verify_the_status_code_for_deleting_the_repo(Integer int3) {
		Assert.assertEquals(int3, response.getStatusCode());
		int3 = response.getStatusCode();
		System.out.println("status code is " + int3);
		
	}

}

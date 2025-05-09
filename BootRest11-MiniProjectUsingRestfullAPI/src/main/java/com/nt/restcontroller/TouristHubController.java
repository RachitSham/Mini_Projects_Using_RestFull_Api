package com.nt.restcontroller;

import java.net.HttpURLConnection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.entity.TouristEntity;
import com.nt.service.ITouristHubMgmtService;
import com.nt.utility.Constants;
import com.nt.vo.ResponseMessage;
import com.nt.vo.TouristVo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/tourist-api")
@Tag(name = "Tourist Journey Joy ", description = "Packed our bags… forgot the itinerary !! Tourist Controller..")
public class TouristHubController {

	@Autowired
	private ITouristHubMgmtService touristHubService;

	// register the tourist
	@PostMapping("/touristregister")
	@Operation(summary = "Tourist details recorded successfully — welcome aboard! ", description = "A seamless process to register and manage tourist information for a smooth travel experience !")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = " Tourist registered Successfully", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = TouristVo.class)) }) })
	public ResponseEntity<ResponseMessage> enrolledTourist(@RequestBody TouristVo touristVo) {
		String enrollTourist = touristHubService.registerTourist(touristVo);
		// 200- tourist enrolled Successfully
		return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
				"Tourist enrolled successfully — Get ready for an exciting journey !! ", enrollTourist));

	}

	// add all batch tourist
	@PostMapping("/touristbatch")
	@Operation(summary = "Tourist Batch details recorded successfully — welcome aboard! ", description = "A seamless process to register and manage tourist batch information for a smooth travel experience !")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = " Tourist Batch registered Successfully", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = TouristVo.class)) }) })
	public ResponseEntity<ResponseMessage> enrolledAllTouristBatch(@RequestBody Iterable<TouristVo> touristVo) {
		String touristBatch = touristHubService.registerTouristBatch(touristVo);
		// 200- tourist Batch enrolled Successfully
		return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
				"Tourist batch registration completed without any issues!", touristBatch));
	}

	// getting all tourist
	@GetMapping("/getalltourist")
	@Operation(summary = "Successfully retrieved all tourist records — explore the full list with ease ! ", description = "Effortlessly access complete tourist data for better insights and management !!")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = " Successfully retrieved all tourist records ", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = TouristVo.class)) }) })
	public ResponseEntity<ResponseMessage> fetchAllToursit() {

		Iterable<TouristVo> allTourist = touristHubService.showAllTourist();
		return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
				"Retrieving complete tourist information !! ", allTourist));
	}

	// fetch tourist by city
	@GetMapping("/touristbycity/{city1}/{city2}/{city3}")
	@Operation(summary = "Successfully fetched tourist records filtered by city — discover who's exploring where!", description = "Easily view all tourists based on their city of visit or residence for location-specific insights ")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = " Successfully fetched tourist records filtered by city ", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = TouristVo.class)) }) })

	public ResponseEntity<ResponseMessage> getTouristByCities(@PathVariable String city1, @PathVariable String city2,
			@PathVariable String city3) {

		List<TouristEntity> tourisCity = touristHubService.showTouristByCities(city1, city2, city3);
		return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
				" Tourist records successfully fetched by city ", tourisCity));

	}

	// finding tourist by id
	@GetMapping("/findtourist/{id}")
	@Operation(summary = "Successfully fetched the tourist's profile using their unique ID — pinpoint accuracy every time!", description = "Quickly access individual tourist information by their unique identifier for precise data handling !")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Successfully fetched the tourist's profile using their unique ID ", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = TouristVo.class)) }) })

	public ResponseEntity<ResponseMessage> getTouristById(@PathVariable int id) {
		TouristEntity touristById = touristHubService.fetchTouristById(id);
		return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
				" Successfully retrieved tourist details by ID ", touristById));

	}

	// finding multiple tourist by ids
	@GetMapping("/findmultipletourist/{id1}/{id2}")
	@Operation(summary = " Successfully fetched multiple tourist records using their unique IDs — fast, reliable, and accurate ", description = "Retrieve detailed information for a group of tourists by providing their unique identifiers in bulk ")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Tourist details retrieved successfully by IDs ", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = TouristVo.class)) }) })
	public ResponseEntity<ResponseMessage> getTouristByMultipleId(@PathVariable int id1, @PathVariable int id2) {
		List<TouristEntity> touristByMultipleId = touristHubService.fetchTouristByMultipleId(id1, id2);
		return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
				"Successfully retrieved the Multiple Tourist details by ID", touristByMultipleId));
	}

	// updated tourist information
	@PutMapping("/updatetouristdata")
	@Operation(summary = "Successfully updated tourist information — keeping records accurate and up-to-date!", description = "Update the details of an existing tourist to maintain accurate and current records")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Tourist data updated successfully.", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = TouristVo.class)) }) })
	public ResponseEntity<ResponseMessage> updateTourist(@RequestBody TouristVo touristVo) {
		String update_msg = touristHubService.updateTouristDetails(touristVo);

		return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
				"Tourist profile updated with new information !! ", update_msg));
	}

	// Fetching the tourist by name
	@GetMapping("/fetchtourist/{name}")
	@Operation(summary = "Tourist details retrieved by name — enabling quick and easy identification!", description = "Search and fetch tourist information using their name for faster access to records !")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Tourist fetched successfully by name !", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = TouristVo.class)) }) })

	public ResponseEntity<ResponseMessage> getTouristByName(@PathVariable("name") String name) {
		List<TouristEntity> touristByName = touristHubService.fetchTouristByName(name);

		return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
				"Tourist details retrieved by name — enabling quick and easy identification !", touristByName));
	}

	// Partial Update operation
	@PatchMapping("/modifybyid/{id}/{percentage}")
	@Operation(summary = "The budget details for the tourist have been successfully updated using their ID.", description = " Easily update individual tourist budgets using a unique identifier for seamless financial management ! ")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Tourist budget modified successfully by ID !", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = TouristVo.class)) }) })

	public ResponseEntity<ResponseMessage> modifyTouristBudgetDetailsById(@PathVariable("id") int id,
			@PathVariable("percentage") double percentage) {
		String updateById = touristHubService.updateTouristById(id, percentage);

		return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
				"Tourist information has been successfully updated using the provided ID !", updateById));

	}

	// delete the tourist by id
	@DeleteMapping("deleteid/{id}")
	@Operation(summary = "Successfully removed the tourist record associated with the given ID !", description = " delete individual tourist records using their unique ID ! ")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = " Tourist deleted by ID !", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = TouristVo.class)) }) })

	public ResponseEntity<ResponseMessage> removeTheTouristById(@PathVariable("id") int id) {

		String deleteById = touristHubService.deleteTouristById(id);
		return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
				"The tourist with the specified ID has been located and is ready for deletion !", deleteById));
	}

	@DeleteMapping("/deletetourist/{start}/{end}")
	@Operation(summary = "Successfully removed the tourist record associated with the given starting and ending budget range !", description = " delete individual tourist records using their ustarting and ending budget range ! ")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = " Tourist deleted by starting and ending budget range !", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = TouristVo.class)) }) })

	public ResponseEntity<ResponseMessage> removeTouristByBudgetRange(@PathVariable("start") double start,
			@PathVariable("end") double end) {
		String touristByBudgetRange = touristHubService.deleteTouristByBudgetRange(start, end);

		return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
				"Tourist records deleted successfully based on the specified budget range ", touristByBudgetRange));
	}
}

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

import com.nt.entity.ActorEntity;
import com.nt.service.IActorMgmtService;
import com.nt.utility.Constants;
import com.nt.vo.ActorVo;
import com.nt.vo.ResponseMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/actor-api")
@Tag(name = "StarCast Central", description = "Your gateway to discovering and managing top acting talent !! ")
public class ActorOperationController {

	@Autowired
	private IActorMgmtService actorMgmtService;

	@PostMapping("/registeractor")
	@Operation(summary = "The actor's information has been added successfully to the system !! ", description = "Seamlessly registers a new actor and stores their details for future reference !!")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = " Actor registered Successfully", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ActorVo.class)) }) })

	public ResponseEntity<ResponseMessage> createActor(@RequestBody ActorVo actorVo) {
		String registerActor = actorMgmtService.registerActor(actorVo);
		// 200- Actor enrolled Successfully
		return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
				"Actor has been successfully registered! Welcome to the cast !!", registerActor));
	}

	@PostMapping("/actorgroup")
	@Operation(summary = " Group of actors created successfully ", description = " Actor group created successfully! Time to organize, collaborate, and shine on stage or screen !! ")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = " Group Of Actors Registered Successfully", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ActorVo.class)) }) })

	public ResponseEntity<ResponseMessage> createGroupOfActors(@RequestBody List<ActorVo> actorVo) {
		String groupoOfActors = actorMgmtService.registerGroupoOfActors(actorVo);
		return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
				"Your actor group is ready — collaborate, cast, and create magic!", groupoOfActors));
	}

	@GetMapping("/displayallactors")
	@Operation(summary = "All actors retrieved and grouped successfully !!", description = "Successfully fetched all actors and created a group — ready to cast, manage, and collaborate !!")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "All actors fetched successfully", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ActorVo.class)) }) })

	public ResponseEntity<ResponseMessage> getAllActors() {
		Iterable<ActorVo> allActors = actorMgmtService.fetchAllActors();
		// 200- Actor Group enrolled Successfully
		return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
				"Successfully retrieved all actor profiles — explore the talent roster now", allActors));
	}

	@GetMapping("/getactorid/{id}")
	@Operation(summary = "Actor details retrieved successfully by ID — access individual profile info instantly !!", description = "This operation allows you to fetch a single actor’s complete profile using their unique identifier(ID)")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Actor details retrieved successfully by ID", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ActorVo.class)) }) })

	public ResponseEntity<ResponseMessage> getActorById(@PathVariable int id) {
		ActorEntity actorById = actorMgmtService.fetchActorById(id);
		return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
				"Actor details retrieved successfully by ID !!", actorById));
	}

	// getting Multiple Actors By Ids
	@GetMapping("/getmultipleActor/{id1}/{id2}")
	@Operation(summary = "Successfully retrieved multiple actors by ID — view details of selected profiles instantly !!", description = "This operation enables efficient retrieval of multiple actor records based on their unique identifiers (IDs) !! ")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Successfully retrieved multiple actors by ID !! ", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ActorVo.class)) }) })

	public ResponseEntity<ResponseMessage> getMultipleActorById(@PathVariable int id1, @PathVariable int id2) {
		List<ActorEntity> multipleActorById = actorMgmtService.fetchMultipleActorById(id1, id2);
		// 200- Actor Fetch by Id Successfully
		return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
				"Successfully retrieved the Multiple Actor details by ID", multipleActorById));
	}

	@PutMapping("/updateactordata")
	@Operation(summary = " Actor profile updated successfully — changes saved and reflected instantly!! ", description = "This feature updates an existing actor’s information such as name, address, or category !!")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Actor profile updated successfully", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ActorVo.class)) }) })

	public ResponseEntity<ResponseMessage> updateActor(@RequestBody ActorVo actorVo) {
		String actorDetails = actorMgmtService.updateActorDetails(actorVo);
		// 200- Actor profile update sucessfully
		return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
				"Actor profile updated with new information !! ", actorDetails));

	}

	@GetMapping("/getactorname/{name}")
	@Operation(summary = "Actor retrieved successfully by name — search completed with accurate match!", description = "his functionality allows you to search for and retrieve actor details using their name !!")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Actor retrieved successfully by name", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ActorVo.class)) }) })

	public ResponseEntity<ResponseMessage> getActorByName(@PathVariable("name") String name) {
		List<ActorEntity> actorByName = actorMgmtService.fetchActorByName(name);
		// 200- Actor retrieved successfully by name
		return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
				"Actor retrieved successfully by name !!", actorByName));
	}

	@DeleteMapping("deleteactor/{id}")
	@Operation(summary = "Actor removed successfully using the provided ID — operation completed cleanly !!", description = "delete individual Actor records using their unique ID !")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "201", description = "Successfully removed the Actor record associated with the given ID ", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ActorVo.class)) }) })
	
	public ResponseEntity<ResponseMessage> removeActorById(@PathVariable int id) {
		String deleteActorById = actorMgmtService.deleteActorById(id);
		// 200- Actor deleted by id..
		return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
				"The Actor with the specified ID has been located and is ready for deletion !", deleteActorById));
	}
	
	@DeleteMapping("deleteactorrange/{start}/{end}")
	@Operation(summary = "Successfully removed the Actor record associated with the given starting and ending fees range !", description = "delete individual actor records using their starting and ending fees range !")
		@ApiResponses(value = {
				@ApiResponse(responseCode = "201", description = "Actor deleted by starting and ending budget range !", content = {
						@Content(mediaType = "application/json", schema = @Schema(implementation = ActorVo.class))
				})
		})
	public ResponseEntity<ResponseMessage> removeActorByFeesRange(@PathVariable("start") double Start, @PathVariable("end") double end) {
		String actorByFeesRange = actorMgmtService.deleteActorByFeesRange(Start, end);
		return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS, "Actor records deleted successfully based on the specified fees range", actorByFeesRange));
	}
}

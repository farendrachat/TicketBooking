package com.infotech.book.ticket.app.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infotech.book.ticket.app.entities.Ticket;
import com.infotech.book.ticket.app.service.TicketBookingService;

@RestController
@RequestMapping(value="/api/tickets")
public class TicketBookingController {

	@Autowired
	private TicketBookingService ticketBookingService;
	
	
	@PostMapping(value="/create")
	public Ticket createTicket(@RequestBody Ticket ticket){
		return ticketBookingService.createTicket(ticket);
	}
	
	@ApiOperation(value = "View a list of available tickets",response = Ticket.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
	@GetMapping(value="/getTicket/{ticketId}")
	//@RequestMapping(value="/ticket/{ticketId}")
	public Ticket getTicketById(@PathVariable("ticketId")Integer ticketId){
		Ticket ticket = ticketBookingService.getTicketById(ticketId);
		return ticket;
	}
	@GetMapping(value="/ticket/alltickets")
	public Iterable<Ticket> getAllBookedTickets(){
		return ticketBookingService.getAllBookedTickets();
	}
	
	@DeleteMapping(value="/deleteTicket/{ticketId}")
	public void deleteTicket(@PathVariable("ticketId")Integer ticketId){
		ticketBookingService.deleteTicket(ticketId);
	}
	
	//@PutMapping(value="/ticket/{ticketId}/{newEmail:.+}")
	@PutMapping(value="/ticket/{ticketId}/{newEmail}")
	public Ticket updateTicket(@PathVariable("ticketId")Integer ticketId,@PathVariable("newEmail")String newEmail){
		Ticket ticket = ticketBookingService.updateTicket(ticketId,newEmail);
		return ticket;
	}
}

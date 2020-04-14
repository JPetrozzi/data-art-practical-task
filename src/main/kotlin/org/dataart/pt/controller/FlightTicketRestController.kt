package org.dataart.pt.controller

import org.dataart.pt.model.FlightTicket
import org.dataart.pt.service.FlightTicketRequest
import org.dataart.pt.service.FlightTicketService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("flight-tickets")
class FlightTicketRestController(private val service: FlightTicketService) {

    @PostMapping
    fun create(@RequestBody request: FlightTicketRequest): FlightTicket = service.create(request)

    @GetMapping
    fun getAll(): Set<FlightTicket> = service.getAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): FlightTicket = service.getById(id)

}

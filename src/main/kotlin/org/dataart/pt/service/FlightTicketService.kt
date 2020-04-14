package org.dataart.pt.service

import org.dataart.pt.model.FlightTicket
import org.dataart.pt.repository.FlightTicketRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.ResponseStatus
import java.math.BigDecimal
import java.time.OffsetDateTime

@Service
class FlightTicketService(private val repository: FlightTicketRepository) {

    fun create(request: FlightTicketRequest): FlightTicket = repository.save(FlightTicket(null,
            request.departureDate, request.arrivalDate, request.cityOfOrigin, request.destinationCity,
            request.passengerName, request.passengerAge, request.luggageStorage, request.price))

    fun getAll(): Set<FlightTicket> = repository.findAll().toSet()

    fun getById(id: Long): FlightTicket = repository.findById(id)
            .orElseThrow { FlightTicketNotFoundException("Sorry, there is not a flight ticket with that ID.") }

}

data class FlightTicketRequest(val departureDate: OffsetDateTime, val arrivalDate: OffsetDateTime,
                               val cityOfOrigin: String, val destinationCity: String, val passengerName: String,
                               val passengerAge: Int, val luggageStorage: Boolean, val price: BigDecimal)

@ResponseStatus(HttpStatus.NOT_FOUND)
class FlightTicketNotFoundException(message: String): RuntimeException(message)

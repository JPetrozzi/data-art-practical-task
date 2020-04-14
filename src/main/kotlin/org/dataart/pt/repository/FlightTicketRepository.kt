package org.dataart.pt.repository

import org.dataart.pt.model.FlightTicket
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface FlightTicketRepository: CrudRepository<FlightTicket, Long>
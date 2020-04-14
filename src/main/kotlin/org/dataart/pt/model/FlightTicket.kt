package org.dataart.pt.model

import java.math.BigDecimal
import java.time.OffsetDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class FlightTicket(@Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long?,
                        val departureDate: OffsetDateTime, val arrivalDate: OffsetDateTime, val cityOfOrigin: String,
                        val destinationCity: String, val passengerName: String, val passengerAge: Int,
                        val luggageStorage: Boolean, val price: BigDecimal)
package org.dataart.pt.service

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.dataart.pt.model.FlightTicket
import org.dataart.pt.repository.FlightTicketRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import java.math.BigDecimal
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.util.*

@ExtendWith(MockKExtension::class)
internal class FlightTicketServiceTest {

    @InjectMockKs
    private lateinit var subject: FlightTicketService

    @MockK
    private lateinit var repository: FlightTicketRepository

    @Test
    fun `create test`() {
        val request = FlightTicketRequest(OffsetDateTime.of(LocalDateTime.now(), ZoneOffset.ofHours(6)),
                OffsetDateTime.of(LocalDateTime.now(), ZoneOffset.ofHours(2)), "Buenos Aires", "New York",
                "Juan Pablo Petrozzi", 27, false, BigDecimal.valueOf(950))
        val expectedFlightTicket = mockk<FlightTicket>()
        val flightTicketSlot = slot<FlightTicket>()
        every { repository.save(capture(flightTicketSlot)) } returns expectedFlightTicket

        subject.create(request)

        assertEquals(request.departureDate, flightTicketSlot.captured.departureDate)
        assertEquals(request.arrivalDate, flightTicketSlot.captured.arrivalDate)
        assertEquals(request.passengerName, flightTicketSlot.captured.passengerName)
        assertEquals(request.passengerAge, flightTicketSlot.captured.passengerAge)
        assertEquals(request.price, flightTicketSlot.captured.price)
        assertEquals(request.cityOfOrigin, flightTicketSlot.captured.cityOfOrigin)
        assertEquals(request.destinationCity, flightTicketSlot.captured.destinationCity)
        assertEquals(request.luggageStorage, flightTicketSlot.captured.luggageStorage)
        verify(exactly = 1) { repository.save(flightTicketSlot.captured) }
    }

    @Test
    fun `get all test`() {
        val flightTickets = listOf(mockk<FlightTicket>())
        every { repository.findAll() } returns flightTickets

        assertEquals(flightTickets.toSet(), subject.getAll())
    }

    @Test
    fun `get by id success test`() {
        val id = 1L
        val idSlot = slot<Long>()
        val expectedFlightTicket = mockk<FlightTicket>()
        every { repository.findById(capture(idSlot)) } returns Optional.of(expectedFlightTicket)

        assertEquals(expectedFlightTicket, subject.getById(id))
        assertEquals(id, idSlot.captured)
        verify(exactly = 1) { repository.findById(idSlot.captured) }
    }

    @Test
    fun `get by id fail test`() {
        every { repository.findById(any()) } returns Optional.empty()

        assertThrows<FlightTicketNotFoundException> { subject.getById(1L) }
    }

}
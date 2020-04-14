package org.dataart.pt.controller

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.dataart.pt.model.FlightTicket
import org.dataart.pt.service.FlightTicketRequest
import org.dataart.pt.service.FlightTicketService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class FlightTicketRestControllerTest {

    @InjectMockKs
    private lateinit var subject: FlightTicketRestController

    @MockK
    private lateinit var service: FlightTicketService

    @Test
    fun `create test`() {
        val request = mockk<FlightTicketRequest>()
        val expectedResponse = mockk<FlightTicket>()
        val requestSlot = slot<FlightTicketRequest>()
        every { service.create(capture(requestSlot)) } returns expectedResponse

        val response = subject.create(request)

        assertEquals(request, requestSlot.captured)
        assertEquals(expectedResponse, response)
    }

    @Test
    fun `get all test`() {
        val expectedResponse = setOf(mockk<FlightTicket>())
        every { service.getAll() } returns expectedResponse

        assertEquals(expectedResponse, subject.getAll())
        verify(exactly = 1) { service.getAll() }
    }

    @Test
    fun `get by id test`() {
        val id = 1L
        val expectedResponse = mockk<FlightTicket>()
        val idSlot = slot<Long>()
        every { service.getById(capture(idSlot)) } returns expectedResponse

        val response = subject.getById(id)

        assertEquals(expectedResponse, response)
        assertEquals(id, idSlot.captured)
    }

}
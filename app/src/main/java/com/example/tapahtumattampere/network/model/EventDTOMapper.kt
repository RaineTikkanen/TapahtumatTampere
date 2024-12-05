package com.example.tapahtumattampere.network.model

import com.example.tapahtumattampere.domain.model.Address
import com.example.tapahtumattampere.domain.model.Coordinates
import com.example.tapahtumattampere.domain.model.Event
import com.example.tapahtumattampere.domain.model.EventDate
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter


class EventDTOMapper{
    private fun formatAddress(addressText: String): Address {
        val parts = addressText.split(", ")
        return when (parts.size) {
            1 -> Address(parts[0])
            2 -> Address(parts[0], parts[1])
            3 -> Address(parts[0], parts[1], parts[2])
            else -> Address(parts[0], parts[1], parts[2], parts[3])
        }
    }

    private fun parseDate(dateString: String): LocalDateTime {
        val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
        val zonedDateTime = ZonedDateTime.parse(dateString, formatter)
        return zonedDateTime.toLocalDateTime()
    }

    private fun mapToDomainModel(model: EventDTO): Event {
        val location: Coordinates?
        val address: Address?
        if (model.locations != null && model.locations.isNotEmpty()) {
            location =
                Coordinates(model.locations[0].geoIndex[0], model.locations[0].geoIndex[1])
            address = formatAddress(model.locations[0].address)
        }
        else{
            location = null
            address = null
        }
        val dates = model.dates.map {
            EventDate(parseDate(it.start), parseDate(it.end))
        }
        return Event(
            id = model.id,
            name = model.name,
            description = model.description,
            location = location,
            address = address,
            startTime = parseDate(model.startTime),
            endTime = parseDate(model.endTime),
            dates = dates,
            url = model.url,
            image = model.images[0].url
        )
    }

    fun fromEntityList(entities: List<EventDTO>): List<Event> {
        return entities.map { mapToDomainModel(it) }
    }
}
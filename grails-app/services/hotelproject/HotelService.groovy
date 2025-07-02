package hotelproject

import grails.gorm.transactions.Transactional

@Transactional
class HotelService {

    def searchHotels(Map params) {

        params.max = Math.min(params.int('max', 10), 100)
        params.offset = params.int('offset', 0)

        def results = Hotel.createCriteria().list(max: params.max, offset: params.offset) {
            if (params.query) {
                ilike('name', "%${params.query}%")
            }
            if (params.countryId) {
                eq('country', Country.get(params.long('countryId')))
            }
            order('stars', 'desc')
            order('name', 'asc')
        }

        return [
                hotels: results,
                hotelCount: results.totalCount,
                country: params.countryId ? Country.get(params.long('countryId')) : null
        ]

    }

    def listHotels() {
        Hotel.list(order: 'name')
    }

    def getHotel(Long id) {
        Hotel.get(id)
    }

    def saveHotel(Hotel hotel) {

        if (hotel.website && !(hotel.website ==~ /^(http|https):\/\/.+/)) {
            hotel.errors.rejectValue('website', 'invalid.url.format')
        }

        if (!hotel.hasErrors() && hotel.save()) {
            return [success: true, hotel: hotel]
        }
        [success: false, hotel: hotel, errors: hotel.errors]

    }

    def updateHotel(Long id, Map params) {

        def hotel = Hotel.get(id)
        if (!hotel) {
            return [success: false, message: "Отель не найден"]
        }

        hotel.properties = params
        if (params.country?.id) {
            hotel.country = Country.get(params.country.id as Long)
        }

        if (!hotel.validate()) {
            return [success: false, hotel: hotel, errors: hotel.errors]
        }

        try {
            hotel.save(flush: true)
            return [success: true, hotel: hotel]
        } catch (Exception e) {
            log.error("Update failed", e)
            return [success: false, hotel: hotel, message: "Ошибка при обновлении"]
        }

    }

    def deleteHotel(Long id) {

        def hotel = Hotel.get(id)
        if (!hotel) {
            return [success: false, message: "Отель не найден"]
        }

        try {
            hotel.delete(flush: true)
            return [success: true, message: "Отель '${hotel.name}' удален"]
        } catch (Exception e) {
            log.error("Delete failed", e)
            return [success: false, message: "Ошибка при удалении отеля"]
        }

    }

    def getCountries() {
        Country.list()
    }
}
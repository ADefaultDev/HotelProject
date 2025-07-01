package hotelproject

import grails.gorm.transactions.Transactional

@Transactional
class HotelController {

    def search() {

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
                query: params.query,
                country: params.countryId ? Country.get(params.long('countryId')) : null,
                countries: Country.list(),
                params: params
        ]
    }

    def list() {
        [hotels: Hotel.list(order: 'name')]
    }

    def create() {
        [hotel: new Hotel(), countries: Country.list()]
    }

    def edit(Long id) {
        def hotel = Hotel.get(id)
        if (!hotel) {
            flash.message = "Отель не найден"
            redirect(action: "list")
            return
        }
        [hotel: hotel, countries: Country.list()]
    }

    @SuppressWarnings(['GroovyAssignabilityCheck', 'MethodCallCanBeStatic'])
    def save(Hotel hotel) {
        if (hotel.save()) {
            flash.message = "Отель '${hotel.name}' сохранен"
            redirect(action: "list")
        } else {
            render(view: "create", model: [hotel: hotel, countries: Country.list()])
        }
    }

    @SuppressWarnings(['GroovyAssignabilityCheck', 'MethodCallCanBeStatic'])
    def update(Long id) {
        def hotel = Hotel.get(id)

        if (!hotel) {
            flash.message = "Отель с ID ${id} не найден"
            redirect(action: "list")
            return
        }

        hotel.properties = params

        if (params.country?.id) {
            hotel.country = Country.get(params.country.id as Long)
        }

        if (hotel.save(flush: true)) {
            flash.message = "Отель '${hotel.name}' успешно обновлён"
            redirect(action: "list")
        } else {
            render view: "edit", model: [hotel: hotel, countries: Country.list()]
        }
    }

    def delete(Long id) {
        def hotel = Hotel.get(id)
        if (hotel) {
            hotel.delete(flush: true)
            flash.message = "Отель '${hotel.name}' удален"
        } else {
            flash.error = "Отель не найден"
        }
        redirect(action: "list")
    }

}
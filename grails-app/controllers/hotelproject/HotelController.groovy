package hotelproject

class HotelController {

    def hotelService

    @SuppressWarnings(['GroovyAssignabilityCheck'])
    def search() {

        try {
            def searchResults = hotelService.searchHotels(params)
            render view: "search", model: [
                    hotels: searchResults.hotels,
                    hotelCount: searchResults.hotelCount,
                    query: params.query,
                    country: searchResults.country,
                    countries: hotelService.getCountries(),
                    params: params
            ]
        } catch (Exception e) {
            log.error("Search error", e)
            flash.error = "Ошибка при поиске отелей"
            redirect action: "list"
        }
    }

    def list() {

        try {
            [hotels: hotelService.listHotels()]
        } catch (Exception e) {
            log.error("List error", e)
            flash.error = "Ошибка при загрузке списка отелей"
            []
        }

    }

    def create() {

        try {
            [hotel: new Hotel(), countries: hotelService.getCountries()]
        } catch (Exception e) {
            log.error("Create form error", e)
            flash.error = "Ошибка при подготовке формы создания"
            redirect action: "list"
        }

    }

    def edit(Long id) {

        try {
            def hotel = hotelService.getHotel(id)
            if (!hotel) {
                flash.message = "Отель не найден"
                redirect(action: "list")
                return
            }
            [hotel: hotel, countries: hotelService.getCountries()]
        } catch (Exception e) {
            log.error("Edit form error", e)
            flash.error = "Ошибка при подготовке формы редактирования"
            redirect action: "list"
        }

    }

    @SuppressWarnings(['GroovyAssignabilityCheck'])
    def save(Hotel hotel) {

        try {
            def result = hotelService.saveHotel(hotel)
            if (result.success) {
                flash.message = "Отель '${hotel.name}' успешно сохранен"
                redirect action: "list"
            } else {
                flash.error = "Ошибка при сохранении отеля"
                render view: "create", model: [
                        hotel: hotel,
                        countries: hotelService.getCountries(),
                        errors: result.errors
                ]
            }
        } catch (Exception e) {
            log.error("Save error", e)
            flash.error = "Серверная ошибка при сохранении отеля"
            redirect action: "create"
        }

    }

    @SuppressWarnings(['GroovyAssignabilityCheck'])
    def update(Long id) {

        try {
            def result = hotelService.updateHotel(id, params)
            if (result.success) {
                flash.message = "Отель '${result.hotel.name}' успешно обновлён"
                redirect action: "list"
            } else {
                flash.error = "Ошибка при обновлении отеля"
                render view: "edit", model: [
                        hotel: result.hotel,
                        countries: hotelService.getCountries(),
                        errors: result.errors
                ]
            }
        } catch (Exception e) {
            log.error("Update error", e)
            flash.error = "Серверная ошибка при обновлении отеля"
            redirect action: "list"
        }

    }

    def delete(Long id) {

        try {
            def result = hotelService.deleteHotel(id)
            if (result.success) {
                flash.message = result.message
            } else {
                flash.error = result.message
            }
        } catch (Exception e) {
            log.error("Delete error", e)
            flash.error = "Серверная ошибка при удалении отеля"
        }
        redirect action: "list"

    }

}
package hotelproject

import grails.gorm.transactions.Transactional

@Transactional
class CountryService {


    def listAllCountries() {
        Country.list(order: 'name')
    }

    def getCountryById(Long id) {
        Country.get(id)
    }

    def saveCountry(Country country) {
        country.save()
    }

    def updateCountry(Country country, Map params) {

        country.properties = params
        country.save(flush: true)

    }

    @Transactional
    def deleteCountry(Long id) {

        def country = Country.get(id)
        if (!country) {
            return [success: false, message: "Страна не найдена"]
        }

        try {
            country.delete(flush: true)
            return [success: true, message: "Страна '${country.name}' и все связанные отели удалены"]
        } catch (Exception e) {
            log.error("Ошибка при удалении страны", e)
            return [success: false, message: "Ошибка при удалении страны"]
        }

    }

}
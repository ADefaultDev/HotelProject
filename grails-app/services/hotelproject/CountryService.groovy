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

    def deleteCountry(Long id) {

        def country = Country.get(id)
        if (!country) {
            return [success: false, message: "Страна не найдена"]
        }

        if (Hotel.countByCountry(country) > 0) {
            return [success: false, message: "Нельзя удалить страну '${country.name}', пока к ней привязаны отели"]
        }

        country.delete(flush: true)
        [success: true, message: "Страна '${country.name}' удалена"]

    }

}
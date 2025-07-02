package hotelproject

import grails.gorm.transactions.Transactional

@Transactional
class CountryController {

    def countryService

    def index() {
        [countries: countryService.listAllCountries()]
    }

    def create() {
        [country: new Country()]
    }

    @SuppressWarnings(['GroovyAssignabilityCheck'])
    def save(Country country) {

        if (countryService.saveCountry(country)) {
            flash.message = "Страна '${country.name}' сохранена"
            redirect(action: "index")
        } else {
            render(view: "create", model: [country: country])
        }

    }

    def edit(Long id) {
        [country: countryService.getCountryById(id)]
    }

    @SuppressWarnings(['GroovyAssignabilityCheck'])
    def update(Long id) {

        def country = countryService.getCountryById(id)
        if (!country) {
            flash.message = "Страна не найдена"
            redirect(action: "index")
            return
        }

        if (countryService.updateCountry(country, params)) {
            flash.message = "Изменения сохранены"
            redirect(action: "index")
        } else {
            render(view: "edit", model: [country: country])
        }

    }

    def delete(Long id) {

        def result = countryService.deleteCountry(id)

        if (result.success) {
            flash.message = result.message
        } else {
            flash.error = result.message
        }

        redirect action: "index"

    }
}
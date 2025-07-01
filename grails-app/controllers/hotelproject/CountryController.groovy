package hotelproject

import grails.gorm.transactions.Transactional

@Transactional
class CountryController {

    def index() {
        [countries: Country.list(order: 'name')]
    }

    def create() {
        [country: new Country()]
    }

    @SuppressWarnings(['GroovyAssignabilityCheck', 'MethodCallCanBeStatic'])
    def save(Country country) {
        if (country.save()) {
            flash.message = "Страна '${country.name}' сохранена"
            redirect(action: "index")
        } else {
            render(view: "create", model: [country: country])
        }
    }

    def edit(Long id) {
        [country: Country.get(id)]
    }

    @SuppressWarnings(['GroovyAssignabilityCheck', 'MethodCallCanBeStatic'])
    def update(Long id) {
        def country = Country.get(id)
        if (!country) {
            flash.message = "Страна не найдена"
            redirect(action: "index")
            return
        }

        country.properties = params

        if (country.save(flush: true)) {
            flash.message = "Изменения сохранены"
            redirect(action: "index")
        } else {
            render(view: "edit", model: [country: country])
        }
    }


    @Transactional
    def delete(Long id) {
        def country = Country.get(id)
        if (!country) {
            flash.message = "Страна не найдена"
            redirect(action: "index")
            return
        }

        if (Hotel.countByCountry(country) > 0) {
            flash.message = "Нельзя удалить страну '${country.name}', пока к ней привязаны отели"
            redirect(action: "index")
            return
        }

        country.delete(flush: true)
        flash.message = "Страна '${country.name}' удалена"
        redirect(action: "index")
    }

}
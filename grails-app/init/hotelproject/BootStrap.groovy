package hotelproject

import grails.util.Environment

class BootStrap {

    def init = { servletContext ->

        if(Environment.current == Environment.DEVELOPMENT) {

            def random = new Random()
            def germany = new Country(name: "Германия", capital: "Берлин").save()
            def vatican = new Country(name: "Ватикан", capital: "Ватикан").save()
            def canada = new Country(name : "Канада", capital: "Оттава").save()
            def usa = new Country(name: "США", capital: "Вашингтон").save()
            def china = new Country(name: "Китай", capital: "Пекин").save()
            def russia = new Country(name: "Россия", capital: "Москва").save()
            def cyprus = new Country(name: "Кипр", capital: "Никосия").save()
            def england = new Country(name: "Великобритания", capital: "Лондон").save()
            def france = new Country(name: "Франция", capital: "Париж").save()
            def australia = new Country(name: "Австралия", capital: "Канберра").save()
            def spain = new Country(name: "Испания", capital: "Мадрид").save()

            new Hotel(
                    name: "Vatican2",
                    country: vatican,
                    stars: 4,
            ).save()

            new Hotel(
                    name: "Vatican",
                    country: vatican,
                    stars: 5,
            ).save()

            new Hotel(
                    name: "Germany",
                    country: germany,
                    stars: 2,
            ).save()

            new Hotel(
                    name: "China",
                    country: china,
                    stars: 4,
            ).save()

            new Hotel(
                    name: "Canada",
                    country: canada,
                    stars: 5,
            ).save()

            new Hotel(
                    name: "Высоцкий",
                    country: russia,
                    stars: 5,
                    website: "https://hotel.tutu.ru/"
            ).save()

            new Hotel(
                    name: "Вознесенский",
                    country: russia,
                    stars: 3,
                    website: "https://v-hotel.ru/"
            ).save()

            new Hotel(
                    name: "Франция",
                    country: france,
                    stars: 5
            ).save()

            def countries = Country.list()

            (1..150).each { i ->
                def randomCountry = countries[random.nextInt(countries.size())]
                def randomStars = 1 + random.nextInt(5)
                def website = (i % 3 == 0) ? "https://hotel${i}.example.com" : null

                new Hotel(
                        name: "Hotel${i}",
                        country: randomCountry,
                        stars: randomStars,
                        website: website
                ).save(failOnError: true)
            }

        }

    }

    def destroy = {}

}
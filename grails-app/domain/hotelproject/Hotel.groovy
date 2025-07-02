package hotelproject

class Hotel {

    String name
    Country country
    Integer stars
    String website

    static constraints = {

        name blank: false, maxSize: 255, unique: 'country'
        country nullable: false
        stars min: 1, max: 5
        website nullable: true,
                validator: { val, obj ->
                    if (val && !(val ==~ /^(http|https):\/\/.+/)) {
                        return 'invalid.url.format'
                    }
                }

    }

    String toString() {
        "${name} (${stars}★)"
    }

}
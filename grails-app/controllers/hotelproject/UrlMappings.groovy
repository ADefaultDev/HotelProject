package hotelproject

class UrlMappings {

    static mappings = {

        "/"(controller: "hotel", action: "search")

        "/country/$action?/$id?"(controller: 'country')
        "/hotel/$action?/$id?"(controller: 'hotel')

        "500"(controller: "error", action: "internalServerError")
        "404"(controller: "error", action: "notFound")
    }

}
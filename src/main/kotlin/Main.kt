import data.baggage.BaggageRegularLocalSource
import data.baggage.BaggageVClubLocalSource
import data.reservation.ReservationSingleton
import domain.datasource.baggage.BaggagePackageDataSource
import domain.model.Flight
import domain.model.Passenger
import domain.model.baggage.pack.BaggagePackage
import domain.model.seat.Seat
import domain.model.seat.SeatSection
import domain.usecases.baggege.GetBaggagePackage
import domain.usecases.baggege.GetBaggageSaved
import domain.usecases.flights.GetFlightSaved
import presentation.utils.Formatter
import domain.usecases.flights.GetFlights
import domain.usecases.flights.di.flightDataDI
import domain.usecases.reservation.AssignTicketToReservation
import domain.usecases.reservation.GetReservation
import domain.usecases.seat.GetSeatBy
import domain.usecases.seat.GetSeatSaved
import domain.usecases.seat.GetSeatsSection
import domain.usecases.ticket.*
import domain.usecases.ticket.di.TicketDataDI
import presentation.PresentationFormat
import presentation.baggage.BaggagePackPresentationFactory
import presentation.baggage.BaggagePackageConsole
import presentation.baggage.BaggagePackageEnun
import presentation.baggage.types.BaggageTypesConsole
import presentation.extfunction.isNumber
import presentation.flight.FlightPresentationFactory
import presentation.flight.formats.FlightConsoleFormat
import presentation.menu.UIInputData
import presentation.menu.UIMenu
import presentation.passenger.PassengerPresentationFactory
import presentation.reservation.ReservationPresentationFactory
import presentation.seat.SeatPresentationFactory
import presentation.seat.section.SeatSectionPresentationFactory
import java.time.Month


fun main(){
    val format = PresentationFormat.CONSOLE
    val flightPresentation = FlightPresentationFactory().getPresentationFormat(format)
    val baggagePackPresentation = BaggagePackPresentationFactory().getPresentationFormat(format)
    val seatSectionPresentation = SeatSectionPresentationFactory().getPresentationFormat(format)
    val seatPresentation = SeatPresentationFactory().getPresentationFormat(format)
    val passengerPresentation = PassengerPresentationFactory().getPresentationFormat(format)
    val reservationPresentation = ReservationPresentationFactory().getPresentationFormat(format)
    val ticketData = TicketDataDI().providesTicketsData()
    val flightData = flightDataDI().providesFlightsData()
    val reservationSingleton = ReservationSingleton()


    val uiMenuFlight = object : UIMenu<Flight>{  }
    val flightMap = GetFlights(flightData).invoke(Month.JANUARY)
    val flightSelected = uiMenuFlight.showMenu(flightMap, flightPresentation)

    AssingFlightToTicket(ticketData).invoke(flightSelected)

    val getFlightSaved = GetFlightSaved(ticketData)
    val flightSave = getFlightSaved()
    println("Flight Saved")
    println(FlightConsoleFormat().format(flightSave))

    //show baggage package
    val baggagePackOption = mapOf(
        1 to BaggagePackageEnun.Regular,
        2 to BaggagePackageEnun.VClub
    )

    val uiMenuBaggagePackOpt = object : UIMenu<BaggagePackageEnun>{}
    val baggagePackageOption = uiMenuBaggagePackOpt.showMenu(baggagePackOption, object: Formatter<BaggagePackageEnun>{
        override fun format(t: BaggagePackageEnun): String = t.name
    } )

    val baggagePackageData = when (baggagePackageOption) {
        BaggagePackageEnun.Regular -> BaggageRegularLocalSource()
        BaggagePackageEnun.VClub -> BaggageVClubLocalSource()
        else -> BaggageRegularLocalSource()
    }

    val baggagePacksMap = GetBaggagePackage(baggagePackageData).invoke()
    val uiMenuBaggagePack = object : UIMenu<BaggagePackage>{}
    val baggagePackageSelected = uiMenuBaggagePack.showMenu(baggagePacksMap,baggagePackPresentation)

    //assign to ticket
    AssignBaggagePackToTicket(ticketData).invoke(baggagePackageSelected)
    val baggagePackSaved = GetBaggageSaved(ticketData).invoke()

    println("Baggage Package Saved")
    println(baggagePackPresentation.format(baggagePackSaved))

    //show available seats
    val seatSectionMap = GetSeatsSection(getFlightSaved).invoke()
    val uiSeatSectionMenu = object : UIMenu<SeatSection>{}
    val seatSectionSelected = uiSeatSectionMenu.showMenu(seatSectionMap, seatSectionPresentation)

    val getSeatsBy = GetSeatBy()
    val seatMap = getSeatsBy(seatSectionSelected)
    val uiSeatsMenu = object : UIMenu<Seat>{}
    val seatSelected = uiSeatsMenu.showMenu(seatMap,seatPresentation)

    //save seat selected
    AssignSeatTicket(ticketData).invoke(seatSelected)
    val seatSaved = GetSeatSaved(ticketData).invoke()
    println("Seat Saved")
    println(seatPresentation.format(seatSaved))

    //Introduce information passenger
    var passengerOty = ""
    do {
        println("How many passengers are?")
        passengerOty = readLine().orEmpty()
    } while (!passengerOty.isNumber())

    val passengers = (1..passengerOty.toInt()).map {
        println("Passenger: $it")
        val uiInputData = object : UIInputData{}
        val name = uiInputData.requestField("Name")
        val email = uiInputData.requestField("Email")
        val phone = uiInputData.requestField("Phone")
        Passenger(name,email, phone)
    }
    AssignPassengerToTicket(ticketData).invoke(passengers)
    println("Show passenger")
    println(passengerPresentation.format(passengers))

    //Show reservation
    AssignTicketToReservation(reservationSingleton, GetTickets(ticketData)).invoke()

    val reservation =  GetReservation(reservationSingleton).invoke()
    println()
    println("** Reservation **")
    println(reservationPresentation.format(reservation))

}

fun printBaggagePacksConsole(
    baggageDataSource: BaggagePackageDataSource
) {
    val getBaggagePackages = GetBaggagePackage(baggageDataSource).invoke()

    getBaggagePackages.forEach { (t, u) ->
        print("$t. ")
        println(BaggagePackageConsole(
            BaggageTypesConsole()
        ).format(u))
    }

}
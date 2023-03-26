# HOTELS'R'US
A hotel booking api rest, which allows users to book rooms in different hotels based on the availability of hotel dates.

Para el uso en Postman, la nomenclatura sería la siguiente:

## HOTEL
/hotels -> Para obtener todos

/hotels/one/{id} -> Para obtener un hotel según su id

/hotels/ins -> Insertar un nuevo hotel

/hotels/mod/{id} -> Para modificar un hotel según su id

## AVAILABILITY
/availabilities -> Para obtener todas

/availabilities/one{id} -> Para obtener una disponibilidad según su id

/availabilities/availability/{id hotel}/{fecha inicio}/{fecha fin}/{habitaciones} -> Añade habitaciones disponibles de un hotel con un rango de fechas y número de habitaciones.

/availabilities/criteria/{fecha de entrada}/{fecha de salida} -> Listados por filtro (NO FINALIZADA SU IMPLEMENTACIÓN)

## BOOKINGS
/bookings -> Para obtener todas

/bookings/group/{id}/{fecha de inicio}/{fecha de fin} -> Obtiene grupo de reservas de un hotel en un rango de fechas

/bookings/booking/{id} -> Para obtener una reserva según su id

/bookings/res/{id hotel}/{fecha de entrada}/{fecha de fin}/{email} -> Realiza una reserva con unas variables

/bookings/del/{id} -> Cancela una reserva

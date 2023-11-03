package dat.ErrorHandling;

import com.google.gson.JsonObject;
import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.time.LocalDateTime;


//#Task 2.3 - ExceptionMapper
public class ExceptionHandler {

    //Task 2.2 - Tilføjer json-object til brugeren, som tilføjer statuskode, besked og timestamp
    //Context context er det context-objekt, som fejlen er sket i. Denne bruges til at sende fejlen tilbage til brugeren
    //context må ikke hedde ctx, da det er det samme som i routes, altså i samme scope
    //Når vi returnerer en Handler, så returnerer vi en lambda, som er en funktion, som tager et context-objekt og returnerer void
    public void ApiExceptionHandler(ApiException e, Context context) {

        //Task 2.2 - Exception returned from an endpointgroup request as a Json object - tilføjer properties efter behov


             //Vi har lavet en record (ErrorMessage), som holder alle vores addProperties fra tidligere.
            context.status(e.getStatusCode());

            //tager vores context, som har et json-objekt som består af statuscode, message og timestamp gennem record'en ErrorMessage
            context.json(new ErrorMessage(e.getStatusCode(), e.getMessage(), LocalDateTime.now().toString()));


            // ---- Det her var første ide, men det driller pga Jackson -----
            // ---- Derfor har vi lavet en record istedet ----

            //return ctx ->{
            //JsonObject responseObject = new JsonObject();

           // responseObject.addProperty("message", e.getMessage());

           // responseObject.addProperty("statusCode", e.getStatusCode());

           // responseObject.addProperty("timeStamp", LocalDateTime.now().toString());

          //  ctx.json(responseObject);
    }
}

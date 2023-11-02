package dat.ErrorHandling;

import com.google.gson.JsonObject;
import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.time.LocalDateTime;


//#Task 2.3 - ExceptionMapper
public class ExceptionHandler {

    //Task 2.2 - Tilføjer json-object til brugeren, som tilføjer statuskode, besked og timestamp
    public Handler APIExceptionHandler(ApiException e, Context context) {
        return (ctx) -> {
            JsonObject response = new JsonObject();
            //Tilføjer statuskode
            response.addProperty("statusCode", e.getStatusCode());
            //Tilføjer besked til bruger
            response.addProperty("message", e.getMessage());
            //Tilføjer timestamp
            response.addProperty("timestamp", LocalDateTime.now().toString());
            //laver response objektet om til json og sendes tilbage til brugeren
            ctx.json(response);
        };
    }
}

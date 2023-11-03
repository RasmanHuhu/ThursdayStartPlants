package dat.config;

import dat.ErrorHandling.ApiException;
import dat.ErrorHandling.ExceptionHandler;
import io.javalin.Javalin;
import io.javalin.apibuilder.EndpointGroup;

public class AppConfig {

    ExceptionHandler exceptionHandler = new ExceptionHandler();

public void startJavalin(EndpointGroup route, int port){
    Javalin app = Javalin.create().start(port);
    app.routes(route);
    app.exception(ApiException.class, exceptionHandler::ApiExceptionHandler);

    //Det her vil ikke fungere, for den kan ikke returner ctx som null
    // exceptionHandler.APIExceptionHandler(new ApiException(404, "Not found"), null);
    }
}

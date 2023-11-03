package dat.ErrorHandling;

public record ErrorMessage(int statusCode, String message, String timestamp) {

    //Denne record ErrorMessage, er lavet fordi Jackson ikke kan parse det vi ønsker

}

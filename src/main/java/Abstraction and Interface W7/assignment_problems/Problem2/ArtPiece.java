public abstract class ArtPiece {
    private static int counter = 1000;
    private final String pieceId;
    protected String title;

    public ArtPiece(String title) {
        this.title = title;
        counter++;
        this.pieceId = "ART-" + counter;
    }

    public abstract String describe();

    public String getPieceId() {
        return pieceId;
    }
}
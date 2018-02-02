import java.util.ArrayList;

/**
 * A simple model of an auction.
 * The auction maintains a list of lots of arbitrary length.
 *
 * @author David J. Barnes and Michael Kölling.
 * @version 2011.07.31
 */
public class Auction
{
    // The list of Lots in this auction.
    private ArrayList<Lot> lots;
    // The number that will be given to the next lot entered
    // into this auction.
    private int nextLotNumber;

    /**
     * Create a new auction.
     */
    public Auction()
    {
        lots = new ArrayList<Lot>();
        nextLotNumber = 1;
    }

    /**
     * Enter a new lot into the auction.
     * @param description A description of the lot.
     */
    public void enterLot(String description)
    {
        lots.add(new Lot(nextLotNumber, description));
        nextLotNumber++;
    }

    /**
     * Show the full list of lots in this auction.
     */
    public void showLots()
    {
        for(Lot lot : lots) {
            System.out.println(lot.toString());
        }
    }

    /**
     * Make a bid for a lot.
     * A message is printed indicating whether the bid is
     * successful or not.
     * 
     * @param lotNumber The lot being bid for.
     * @param bidder The person bidding for the lot.
     * @param value  The value of the bid.
     */
    public void makeABid(int lotNumber, Person bidder, long value)
    {
        Lot selectedLot = getLot(lotNumber);
        if(selectedLot != null) {

            boolean successful = selectedLot.bidFor(new Bid(bidder, value));
            if(successful) {
                System.out.println("The bid for lot number " +
                    lotNumber + " was successful.");
            }
            else {
                // Report which bid is higher.

                System.out.println("Lot number: " + lotNumber +
                    " already has a bid of: " +
                    selectedLot.getHighestBid().getValue());
            }
        }
    }

    /**            
     * Return the lot with the given number. Return null
     * if a lot with this number does not exist.
     * @param lotNumber The number of the lot to return.
     */
    public Lot getLot(int lotNumber)
    {   int cont =-1;
        Lot selectedLot = null;
        for(Lot objetoSub : lots){
            cont ++;
            if(objetoSub.getNumber() == lotNumber){
                selectedLot = lots.get(cont);
            }

        }

        return selectedLot;

    }

    public void close(){
        for(Lot objetosSub : lots){
            System.out.println(objetosSub.toString());
            Bid pujaMayor = objetosSub.getHighestBid();
            if (pujaMayor == null){
                System.out.println("no ha habido pujas por este objeto");
            }
            else{
                System.out.println("el ganaor de la puja es:"+ pujaMayor.getBidder().getName());

                System.out.println("el precio del articulo es:"+ pujaMayor.getValue());

            }
        }
    }

    public  ArrayList<Lot> getUnsold(){
        ArrayList<Lot> getUnsold = new ArrayList<Lot>();

        for(Lot objetosSub : lots){

            Bid pujaSinVender = objetosSub.getHighestBid();
            if (pujaSinVender == null){
                getUnsold.add(new Lot(objetosSub.getNumber(),objetosSub.getDescription()));
                System.out.println(objetosSub.toString());
            }

        }
        return getUnsold;

    }

    /**
     * elimina el lote con en numero dado
     * param numberel numero de lote que has eliminado
     * retun el numero del lote que has eliminado o null
     * o no existe ese numero de lote
     */ 

    public Lot removeLot(int number){
        int cont =0; 
        boolean busqueda = true;
        Lot devolver = null;
        while(cont<= lots.size() && busqueda){
            Lot selectedLot = lots.get(cont);
            
            if(selectedLot.getNumber() == number){
                devolver = lots.get(cont);
                lots.remove(cont);
                busqueda = false;
                
            }
            else {

                cont ++;
            }
        }
        if(busqueda){

            return null;
        }
        else{
            return devolver;
        }
    }
}






package by.it.zavadski.jd02_03;

import java.util.ArrayList;

import static by.it.zavadski.jd02_03.Util.random;

public class Buyer extends Thread implements IBuyer, IUseBasket {
    public void setWaiting(boolean waiting) {
        this.waiting = waiting;
    }

    private boolean waiting=false;





    Buyer (){
        super("Buyer-"+Dispatcher.buyerInMarket());
            }

    @Override
    public String toString() {
        return this.getName();
    }

    @Override
    public void run() {
        enterToMarket();
        takeBasket();
        chooseGoods();
        putGoodsToBasket();
        try {
            jumpInQueue();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        goOut();
    }

    @Override
    public void enterToMarket() {
        System.out.printf("%-10s ENTERED\n",this);
    }

    @Override
    public void jumpInQueue() throws InterruptedException {
        System.out.println(this+"->Jump in queue");
        QueueBuyers.add(this);
        waiting=true;
        System.out.println(this+"<-Leave Cashdesk");
        while (waiting)
            synchronized (this){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
    }

    @Override
    public void chooseGoods() {
        System.out.printf("%-10s choosing goods\n",this);
        int timeout = random(2000);
        Util.sleep(timeout);
        System.out.printf("%-10s chose goods\n",this);
    }

    @Override
    public void goOut() {
        System.out.printf("%-10s LEFT\n",this);
        Dispatcher.buyerLeaveMarket();
    }

    @Override
    public void takeBasket() {
        System.out.printf("%-10s took basket\n",this);
    }

    @Override
    public void putGoodsToBasket() {
        StringBuilder basket=new StringBuilder();
        long timeStamp=System.nanoTime();
        Util.sleep(random(100,200));
        ArrayList<String> goodsNames=new ArrayList<>(Dispatcher.chooseGoods().keySet());
        //System.out.printf("%-10s put in basket:\n",this);
        for (int i = 0; i <random(1,4) ; i++) {
            String inBasket=goodsNames.get(random(0,goodsNames.size()-1));
            Double price= Dispatcher.chooseGoods().get(inBasket);
            basket.append(inBasket).append("-$").append(price).append(" ");
            //System.out.printf("%9s put in basket:\n\t%tT %-9s %-3.2f \n",this,timeStamp,basket.toString(),price);
        }
       System.out.printf("%9s put in basket:\n\t%tT %-9s \n",this,timeStamp,basket.toString());
    }
}

package by.it.yuntsevich.jd02_01;

import java.util.HashMap;
import java.util.Map;

public class Buyer extends Thread implements IBuyer, IUseBucket {
    private Map<String, Double> bucket = null;
    private boolean pensioneer = false;

    Buyer(int num) {

        super("Покупатель № " + num + " ");
    }

    @Override
    public void run() {
        enterToMarket();
        takeBucket();
        chooseGoods();
        goOut();
    }

    @Override
    public String toString() {
        return this.getName();
    }

    @Override
    public void enterToMarket() {
        System.out.println(this + "вошел в магазин");
    }

    @Override
    public void chooseGoods() {
        System.out.println(this + "начал выбирать товары");

        int timeout;
        if (pensioneer) {
            yield();
            timeout = Util.random(2000, 3000);
        } else {
            timeout = Util.random(2000);
        }
        Util.sleep(timeout);
        putGoodsToBucket();
        System.out.println(this + "выбрал товары");

    }


    @Override
    public void goOut() {
        System.out.println(this + "вышел из магазина");
    }

    @Override
    public void takeBucket() {
        int timeout;
        bucket = new HashMap<>(4);
        if (pensioneer) {
            timeout = Util.random(1000, 2000);
        } else {
            timeout = Util.random(100, 200);
        }
        Util.sleep(timeout);
        System.out.println(this + "взял корзину");
    }

    @Override
    public void putGoodsToBucket() {
        int countGoods = Util.random(1, 4);
        for (int i = 1; i <= countGoods; i++) {
            int timeout;
            if (pensioneer) {
                timeout = Util.random(1000, 2000);
            } else {
                timeout = Util.random(100, 200);
            }
            Util.sleep(timeout);
            Map.Entry<String, Double> entry = Runner.takeGoods();
            if (entry != null) {
                bucket.put(entry.getKey(), entry.getValue());
                System.out.println(this + "кладет " + entry.getKey() + " стоимостью " + entry.getValue() + " BYN в корзину.");
            }
        }


    }

    public void setPensioneer(boolean pensioneer) {
        this.pensioneer = pensioneer;
    }
}

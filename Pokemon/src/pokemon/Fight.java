package pokemon;

/**
 * Created by Игорь on 09.12.2016.
 */
public interface Fight {
    /**
     * @return damage which figter  deal
    */
    int Damage();

    /**
     * @return ultimate damage which figter  deal
     */
    int DamageUlt();

    /**
     * @return  figter's health
     */
    int getEnergy();

    /**
     * Set fighter's health after
     * @param damage  by an opponent
     */
    void setEnergy(int damage);


    /**
     * Character defence
     * @param damage by opponent
     */
    void protection(int damage);

    /**
     *  @return true if fighter is alive
     */
    boolean isAlive();
}

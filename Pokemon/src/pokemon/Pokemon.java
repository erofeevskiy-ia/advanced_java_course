package pokemon;

/**
 * Created by Игорь on 09.12.2016.
 */
 class Pokemon implements Fight, Training, Recovery {
    @Override
    public void recovering() {
        setStatus(status.EAT);
        this.energy=health;
    }

    enum status {
        SLEEP,
        EAT,
        GAMING,
        TRAINING,
        FITING
    }

    private int health;
    private int level;
    private int energy;
    private int power;
    private int protect;
    private String name;
    private status statusPokemon;
    //pokemon.Trainer trainer;

    Pokemon(String name,int health, int level,int power)
    {
        this.health=health;
        this.level=level;
        this.energy = health;
        this.power=power;
        this.name=name;
        //this.trainer = trainer;
        //System.out.println(" create pock: p="+power+",ene="+energy+"heal="+health);

    }

    public void setStatus(status stat){
        statusPokemon=stat;
    }

    public status getStatus(){
        return this.statusPokemon;
    }

    @Override
    public int Damage() {
        int dam = power + level;
        System.out.println("Damage by "+ this.getName() + " = " + dam);
        return dam;
    }

    @Override
    public int DamageUlt() {
        int dam = power + level*2;
        System.out.println("Ultimate Damage by "+ this.getName() +" = " + dam);
        return dam;
    }

    @Override
    public int getEnergy() {
        return energy;
    }

    @Override
    public void setEnergy(int val ) {
        this.energy=val;

    }

    String getName(){
        return this.name;
    }

    @Override
    public boolean isAlive() {
        if (this.energy>0) return true;
        else return false;
    }

    @Override
    public void protection(int damage) {
        setEnergy(this.energy - damage);
        System.out.println(getName() + " gets Damage and his energy is " + this.getEnergy());
    }

    @Override
    public boolean train() {
        setStatus(status.TRAINING);
        System.out.println("Training for "+getName() + " start");
        setEnergy(this.energy+this.level*2);
        this.health=this.energy;
        this.power=power+level;
        level+=1;
        System.out.println("New Characteristics of " + getName() + "\n"+
                "Energy = "+ energy + "\n" +
                "Power = " + power +  "\n" +
                "Level = " + level);

        System.out.println("Training "+ getName() +" finish \n");
        setStatus(status.SLEEP);
        return true;
    }


}

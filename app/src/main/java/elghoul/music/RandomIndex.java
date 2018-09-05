package elghoul.music;

class RandomIndex extends Next{


    RandomIndex(int Current, int Length) {
        Index( Current,Length );
    }

    void Index(int Current, int Length) {
        int random;
        do{
            random= new java.util.Random(  ).nextInt( Length );
        }while (random==Current);
            setIndex( random );

    }



}

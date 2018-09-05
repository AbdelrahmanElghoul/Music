package elghoul.music;

import java.util.Deque;

class Index {

    public Next getNext(Boolean Random,int Current,int Length) {
        if(Random) {
            return new RandomIndex( Current, Length );
        }else{
            return new NextIndex( Current,  Length);

        }
    }

    public Previous getPrevious(int CurrentIndex, Deque<Integer> History, int Length) {
        return new Previous(CurrentIndex,History,Length);
    }
}

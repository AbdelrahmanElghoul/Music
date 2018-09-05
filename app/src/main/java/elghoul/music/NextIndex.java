package elghoul.music;

class NextIndex extends Next {
     NextIndex(int Current, int Length) {
         Index( Current,Length );
    }

    void Index(int Current, int Length) {
       setIndex(++Current);
    }

}


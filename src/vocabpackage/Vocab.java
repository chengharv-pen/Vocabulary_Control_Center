package vocabpackage;

/**
 *
 * This class defines the Vocab object. It contains a default and custom constructors as
 * well as getters, setters, equals and toString methods.
 *
 */
public class Vocab {

    private String word;
    private String topic;

    public Vocab() {
        this.word = null;
        this.topic = null;
    }

    public Vocab(String w, String t) {
        this.word = w;
        this.topic = t;
    }

    public String getWord() {
        return word;
    }

    public String getTopic() {
        return topic;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String toString() {
        return "Word: " + this.word + ", Topic: " + this.topic;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            Vocab vocabObj = (Vocab) obj;
            if (this.word.equals(vocabObj.word) && this.topic.equals(vocabObj.topic)) {
                return true;
            } else {
                return false;
            }
        }
    }

}

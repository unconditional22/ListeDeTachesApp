import android.os.Parcel;
import android.os.Parcelable;

public class Tache implements Parcelable {
    private String description;
    private boolean estTerminee;

    public Tache(String description, boolean estTerminee) {
        this.description = description;
        this.estTerminee = estTerminee;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isEstTerminee() {
        return estTerminee;
    }

    public void setEstTerminee(boolean estTerminee) {
        this.estTerminee = estTerminee;
    }

    // Parcelable implementation
    protected Tache(Parcel in) {
        description = in.readString();
        estTerminee = in.readByte() != 0;
    }

    public static final Creator<Tache> CREATOR = new Creator<Tache>() {
        @Override
        public Tache createFromParcel(Parcel in) {
            return new Tache(in);
        }

        @Override
        public Tache[] newArray(int size) {
            return new Tache[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(description);
        dest.writeByte((byte) (estTerminee ? 1 : 0));
    }
}

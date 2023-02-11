package br.com.fiap.abctechapi.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLocation {

    public static final double MAX_DISTANCE = 10;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private double latitude;
    private double longitude;
    private Date date;

    public boolean validLatitude () { return latitude >= -90 && latitude <= 90; }

    public boolean validLongitude () { return longitude >= -180 && longitude <= 180; }

    public double getMaxDistance () { return MAX_DISTANCE; }

    public boolean validDistance (double lat, double lon) { return this.distance(lat, lon) <= MAX_DISTANCE; }

    public double distance(double lat, double lon) {
        double R = 6371000;
        double dLat = Math.toRadians(lat-latitude);
        double dLng = Math.toRadians(lon-longitude);
        double a = Math.sin(dLat/2)
                 * Math.sin(dLat/2)
                 + Math.cos(Math.toRadians(lat))
                 * Math.cos(Math.toRadians(latitude))
                 * Math.sin(dLng/2)
                 * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        float dist = (float) (R * c);

        return dist;
    }
}

package pl.mzlnk.emergencyspot.model.hospitalward;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.mzlnk.emergencyspot.R;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum HospitalWardEnum {

    ISOLATION(R.drawable.d_ward_type_isolation, "oddział zakaźny"),
    PEDIATRY(R.drawable.d_ward_type_pediatry, "pediatria"),
    CARDIOLOGY(R.drawable.d_ward_type_cardiology, "kardiologia"),
    SURGERY(R.drawable.d_ward_type_surgery, "chirurgia"),
    ONCOLOGY(R.drawable.d_ward_type_oncology, "onkologia"),
    GERIATRICS(R.drawable.d_ward_type_geriatrics, "geriatria"),
    OPHTHAMOLOGY(R.drawable.d_ward_type_ophthamology, "okulistyka"),
    ICU(R.drawable.d_ward_type_icu, "OIOM"),
    ER(R.drawable.d_wart_type_er, "SOR");

    private int iconResId;
    private String name;

}


package com.healthkart;
public class Medicines
{
    private String medicineId;
    private String medicineName;
    private String shortDescription;
    private int price;

    /**
     * @return the medicineId
     */
    public String getMedicineId()
    {
        return medicineId;
    }

    /**
     * @param medicineId the medicineId to set
     */
    public void setMedicineId(String medicineId)
    {
        this.medicineId = medicineId;
    }

    /**
     * @return the medicineName
     */
    public String getMedicineName()
    {
        return medicineName;
    }

    /**
     * @return the shortDescription
     */
    public String getShortDescription()
    {
        return shortDescription;
    }

    /**
     * @return the price
     */
    public int getPrice()
    {
        return price;
    }

    /**
     * @param medicineName the medicineName to set
     */
    public void setMedicineName(String medicineName)
    {
        this.medicineName = medicineName;
    }

    /**
     * @param shortDescription the shortDescription to set
     */
    public void setShortDescription(String shortDescription)
    {
        this.shortDescription = shortDescription;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(int price)
    {
        this.price = price;
    }
}

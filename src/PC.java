import java.util.ArrayList;

/**
 * Created by IMac-Windows on 27/03/2016.
 */
public class PC
{
    public ArrayList<Component> userCfg;
    public String notCompatible;
    private float totWattUsage;
    private boolean check;
    private int amount;

    public PC()
    {
        userCfg = new ArrayList<>();
        this.notCompatible = notCompatible;
        this.totWattUsage = totWattUsage;
        this.check = check;
        this.amount = amount;

    }

    public boolean checkCompatibility()
    {
        // Info from Motherboard
        String socketMB = "", ramSlotsMB = "", maxRamMB = "", ramTypeMB = "";
        // Info from CPU
        String socketCPU = "";
        // Info from RAM
        String typeRAM = "", amountGBRAM = "", amountSticksRAM = "";
        // Info from PSU
        String maxWattage = "";
        // Booleans for final check
        boolean checkSocket = false, checkRAMSlots = false, checkMaxRAM = false, checkRAMType = false, checkWattUsage = false;
        // Components used
        Motherboard mb = null;
        CPU cpu = null;
        RAM ram = null;
        PSU psu = null;

        float CalculatedWattUsage = totWattUsage;

        if (userCfg.size() == 6)
        {
            // Reading the necessary info for check
            for (Component component : userCfg)
            {
                switch (component.getGroupComponent())
                {
                    case "Motherboard":
                        mb = (Motherboard) component;
                        socketMB = mb.getSocketMB();
                        ramSlotsMB = mb.getRAMSlots();
                        maxRamMB = mb.getMaxRAM();
                        ramTypeMB = mb.getRamType();
                        break;
                    case "CPU":
                        cpu = (CPU) component;
                        socketCPU = cpu.getSocketCPU();
                        break;
                    case "RAM":
                        ram = (RAM) component;
                        typeRAM = ram.getTypeRAM();
                        amountGBRAM = ram.getAmountGB();
                        amountSticksRAM = ram.getAmountSticks();
                        break;
                    case "PSU":
                        psu = (PSU) component;
                        maxWattage = psu.getMaxWattage();
                        break;
                }
            }
            notCompatible = "";
            // Compatibility check
            for (int i = 0; i < 5; i++)
            {
                switch (i)
                {
                    case 0:
                        if (socketMB.equals(socketCPU))
                        {
                            checkSocket = true;
                        } else
                        {
                            notCompatible += "Socket from CPU: " + cpu.getNameComponent() + " (" + socketCPU + ") is not compatible with the chosen motherboard: " + mb.getNameComponent() + " (" + socketMB + ").\n";
                        }
                        break;
                    case 1:
                        if (ramSlotsMB.equals(amountSticksRAM))
                        {
                            checkRAMSlots = true;
                        } else if (Integer.parseInt(ramSlotsMB) > Integer.parseInt(amountSticksRAM))
                        {
                            checkRAMSlots = true;
                        } else
                        {
                            checkRAMSlots = false;
                            if (notCompatible.equals(""))
                            {
                                notCompatible += "\n";
                            }
                            notCompatible += "The amount of slots the RAM (" + amountSticksRAM + " sticks) is more than the slots available on the motherboard" + ramSlotsMB + " slots.\n";
                        }
                        break;
                    case 2:
                        if (maxRamMB.equals(amountGBRAM))
                        {
                            checkMaxRAM = true;
                        } else if (Integer.parseInt(maxRamMB) > Integer.parseInt(amountGBRAM))
                        {
                            checkMaxRAM = true;
                        } else
                        {
                            checkMaxRAM = false;
                            if (notCompatible.equals(""))
                            {
                                notCompatible += "\n";
                            }
                            notCompatible += "The motherboard (" + mb.getNameComponent() + ") " + "can have up to " + maxRamMB + " of RAM. The selected RAM has " + amountGBRAM + ".\n";
                        }
                        break;
                    case 3:
                        if (ramTypeMB.equals(typeRAM))
                        {
                            checkRAMType = true;
                        } else
                        {
                            checkRAMType = false;
                            if (notCompatible.equals(""))
                            {
                                notCompatible += "\n";
                            }
                            notCompatible += "Ram type of the selected RAM: " + ram.getNameComponent() + " (" + typeRAM + ") " + "is not compatible with the selected motherboard: " + mb.getNameComponent() + " (" + ramTypeMB + ").\n";
                        }
                        break;
                    case 4:
                        // A PSU needs a safety margin of minimum 30%
                        float headRoomIncluded = Float.parseFloat(maxWattage) - Float.parseFloat(maxWattage) * (1 / 3);
                        if (headRoomIncluded > CalculatedWattUsage)
                        {
                            checkWattUsage = true;
                        } else
                        {
                            checkWattUsage = false;
                            if (notCompatible.equals(""))
                            {
                                notCompatible += "\n";
                            }
                            notCompatible += "Total Watt Usage from components: " + CalculatedWattUsage + " Watt is higher than the Watt (power) provided by the Power supply (" + psu.getNameComponent() + ")" + maxWattage + "(headRoom included: " + headRoomIncluded + ").\n";
                        }
                        break;
                }
            }

            // Final Check

            if (checkSocket && checkRAMSlots && checkMaxRAM && checkRAMType && checkWattUsage)
            {
                return check = true;
            } else
            {
                return check = false;
            }

        } else
        {
            PCBuilder.gui.setErrorPanel("Don't have enough components for compatibility check !");
            return false;
        }
    }

    public boolean addComponent(Component component, String name)
    {
        if (!name.equals(""))
        {
            boolean duplicate = false;
            if (userCfg.size() < 6)
            {
                for (Component comp : userCfg)
                {
                    if (component.getGroupComponent().equals(comp.getGroupComponent()))
                    {
                        duplicate = true;
                        System.out.println("Duplicate element found : " + component.getGroupComponent());
                    }
                }

                if (!duplicate)
                {
                    userCfg.add(component);
                }
                System.out.println("Duplicate: " + duplicate);
                return duplicate;
            }
        }
        return true;
    }

    // connect with button
    public void removeComponent(Component component)
    {
        int removeIndex = 100;
        String componentName = component.getNameComponent();

        for (int i = 0; i < userCfg.size(); i++)
        {

            component = userCfg.get(i);
            if (componentName.equals(component.getNameComponent()))
            {
                removeIndex = i;
            }
        }

        userCfg.remove(removeIndex);
    }

    public float calculateWattUsage()
    {
        totWattUsage = 0;
        System.out.println("calcwatt");
        if(userCfg.size() == 6)
        {
            for(Component component : userCfg)
            {
                if (!component.getGroupComponent().equals("PSU"))
                {
                    totWattUsage += Float.parseFloat(component.getWattUsage());
                }
            }
        } else if (PCBuilder.gui.setCalcWattVerification())
        {
            for (Component component : userCfg)
            {
                if (!component.getGroupComponent().equals("PSU"))
                {
                    totWattUsage += Float.parseFloat(component.getWattUsage());
                }
            }
        }
        System.out.println("Tot Watt Usage: " + totWattUsage);
        return totWattUsage;
    }

}

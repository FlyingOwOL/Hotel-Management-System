package MainProgram;
public class Main
{
	public static void main(String[] args) 
	{
	    int menuInput=2;
	    do{
    		System.out.println("Select option:");
    		System.out.println("1.Create Hotel");
    		System.out.println("2.View Hotel");
    		System.out.println("3.Manage Hotel");
    		System.out.println("4.Book Room");
    		System.out.println("5.Exit Program");
            System.out.println("Number of your choice:");
            switch(menuInput)
            {
                case 1:
                    System.out.println("Option has yet to be implemented");
                    break;
                case 2:
                    System.out.println("Option has yet to be implemented");
                    break;
                case 3:
                    System.out.println("Option has yet to be implemented");
                    break;
                case 4:
                    System.out.println("Option has yet to be implemented");
                    break;
                case 5:
                    System.out.println("Now exiting program");
                    break;
                default:
                    System.out.println("Invalid input");
                
            }
	    }while(menuInput!=5);
	}
}

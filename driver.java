import java.util.Scanner;

// -----------------------------------------------------
// Assignment 2
// ©Samuel Fauteux
// Written by:Samuel Fauteux 202033158
// -----------------------------------------------------
// This is a computer store management program(the main is here), 
// for some reasons, this store only contains computers and we don't save the inputed info
// -----------------------------------------------------

public class driver {
	static String Password = "123"; //I like very secure passwords
	
	// Takes the number of tries allowed to enter the password 
	// and asks the user to enter the password until successful 
	// of the number of tries maximum is reached, if the right 
	// password was entered, returns true, if not, returns false
	static boolean EnterPassword(int nbOfTries, Scanner s) {
		for(int i = 0; i < nbOfTries; ++i)
		{
			System.out.println("Plese enter the password: ");
			if(s.nextLine().equals(Password))
			{
				return true;
			}
			System.out.println("Wrong password!");
		}
		System.out.println("To many wrong attemps!");
		return false;
	}
	
	// This method creates computers based on user input and then put 
	// the created computer in the Computer array you passed when calling it
	static void EnterNewComputers(Computer[] inventory, Scanner s) {
		if(EnterPassword(3, s)) 
		{
			System.out.println("How many computers do you want to enter? ");
			int nbOfComputerToCreate = s.nextInt();
			s.nextLine(); //to clear the buffer
			
			if((nbOfComputerToCreate + Computer.findNumberOfCreatedComputers()) > inventory.length) //if let
			{
				System.out.println("Your store cannot hold so many computers! You only have " + (inventory.length - Computer.findNumberOfCreatedComputers()) + " spaces left!"
						+ "\n..returning to the main menu.");
			}
			else
			{
				for(int i = 0; i < nbOfComputerToCreate; ++i)
				{
					System.out.print("Brand: ");
					String brand = s.nextLine();
					System.out.print("Model: ");
					String model = s.nextLine();
					System.out.print("Serial Number: ");
					long sn = s.nextLong();
					s.nextLine();
					System.out.print("Price: ");
					double price = s.nextDouble();
					s.nextLine(); 
					
					Computer computer = new Computer(brand, model, sn, price);
					inventory[Computer.findNumberOfCreatedComputers() - 1] = computer; //since there is no way to remove a computer in this program, the desired index = to the number of computer created so far
				}
				System.out.println("Task done, you successfully added " + nbOfComputerToCreate + " to the system. You could add " + (inventory.length - Computer.findNumberOfCreatedComputers()) + " more.");
			}
		}
	}
	
	static Computer[] IncreaseArraySize(Computer[] inventory)
	{
		int sizeToAdd = 4; //constantly increasing of 4 because this is not implemented and I didn't feel to write a print
		Computer[] newArray = new Computer[inventory.length + sizeToAdd];
		
		for(int i = 0; i < inventory.length; ++i)
		{
			newArray[i] = inventory[i];
			++i;
		}
		
		return newArray;
	}
	
	// Add a line to the standard computer display, the computer number... it doesn't 
	// even find it, you pass it to it
	static void DisplayComputerWithComputerNumber(int computerNumber, Computer computer) 
	{
		System.out.print("\n// -----------------------------------------------------\n"
				+ "Computer Number:  " + computerNumber + computer);
	}
	
	// Changes the informations into a Computer from the Computer array you passed it
	static void ChangeComputerInformation(Computer[] inventory, Scanner s)
	{
		if(EnterPassword(3, s))
		{
			boolean userIsDone = false;
			
			do
			{
				System.out.println("Give us the number of the computer you want to modify or x to close the program: ");
				String input = s.nextLine();
				
				try
				{
					int inputtedNumber = Integer.parseInt(input);
					
					if(inputtedNumber >= 0 && inputtedNumber < Computer.findNumberOfCreatedComputers()) //once again, I can use the number of computer in this way because I don't remove any in this program
					{
						DisplayComputerWithComputerNumber(inputtedNumber, inventory[inputtedNumber]);
						
						int chosenOption = 0;
						do
						{
							System.out.println("What information would you like to change?\n"
									+ "	1. brand\n"
									+ "	2. model\n"
									+ "	3. SN\n"
									+ "	4. price\n"
									+ "	5. Quit\n"
									+ "Enter your choice>\n");
							chosenOption = s.nextInt();
							s.nextLine();
							
							switch(chosenOption)
							{
								case 1: 
									System.out.print("\nBrand: ");
									inventory[chosenOption].setBrand(s.nextLine());
									DisplayComputerWithComputerNumber(inputtedNumber, inventory[inputtedNumber]);
									break;
								case 2: 
									System.out.print("\nModel: ");
									inventory[chosenOption].setModel(s.nextLine());
									DisplayComputerWithComputerNumber(inputtedNumber, inventory[inputtedNumber]);
									break;
								case 3: 
									System.out.print("\nSN: ");
									inventory[chosenOption].setSN(s.nextLong());
									s.nextLine();
									DisplayComputerWithComputerNumber(inputtedNumber, inventory[inputtedNumber]);
									break;
								case 4: 
									System.out.print("\nPrice: ");
									inventory[chosenOption].setPrice(s.nextDouble());
									s.nextLine();
									DisplayComputerWithComputerNumber(inputtedNumber, inventory[inputtedNumber]);
									break;
								case 5: break;
								default: System.out.println("Please enter the number corresponding to your desired option. (1 to 5)");
							}
						} while(chosenOption != 5);
					}
				}
				catch(NumberFormatException e)
				{
					if(input.equals("x"))
					{
						userIsDone = true;
					}
					else
					{
						System.out.println("Input unreconized.");
					}
				}
			}while(!userIsDone);
		}
	}
	
	//Make an array contening all Computer from the passed Computer array
	static int[] findComputersByBrand(Computer[] computerList, String brand) {
		int[] indexList = new int[computerList.length];
		int indexListIndex = 0;
		int computerIndex = 0;
		for(Computer computer : computerList)
		{
			if(computer.getBrand().equals(brand))
			{
				indexList[indexListIndex] = computerIndex;
				++indexListIndex;
			}
			++computerIndex;
		}
		
		int[] shorterIndexList = new int[indexListIndex + 1];
		for(int i = 0; i < shorterIndexList.length; ++i)
		{
			shorterIndexList[i] = indexList[i];
		}
		return shorterIndexList;
	}
	
	// Asks of which bran you want to display the computers and display the 
	// matching results from the passed Computer array
	static void DisplayAllComputersOfABrand(Computer[] computerList, Scanner s) 
	{
		System.out.println("Which brand of computer interests you? ");
		String brand = s.nextLine();
		
		int[] brandIndexes = findComputersByBrand(computerList, brand); 
		
		for(int i : brandIndexes)
		{
			System.out.print(computerList[i]);
		}
	}
	
	// Asks for a price and display all Computer from the passed Computer array
	// that has a lower price
	static void DisplayCheaperComputerThan(Computer[] inventory, Scanner s)
	{
		System.out.println("Under which price must the computers be? ");
		double price = s.nextDouble();
		s.nextLine();
		
		for(int i = 0; i < Computer.computerCounter; ++i)
		{
			if(inventory[i].getPrice() < price)
			{
				System.out.print(inventory[i]);
			}
		}
	}

	// This is the main, it contains the main menu and the welcoming message
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("Welcome USER. \nHow many computers can your store contain? ");
		Computer[] inventory = new Computer[s.nextInt()];
		s.nextLine();
		
		int chosenOption = 0;
		do {
			System.out.println("What do you want to do?\n"
					+ "	1. Enter new computers (password required)\n"
					+ "	2. Change information of a computer (password required)\n"
					+ "	3. Display all computers by specific brand\n"
					+ "	4. Display all computers under a specific price.\n"
					+ "	5. Quit\n"
					+ "Please enter your choice>\n");
		
			chosenOption = s.nextInt();
			s.nextLine();
		
			switch(chosenOption)
			{
				case 1: EnterNewComputers(inventory, s); break;
				case 2: ChangeComputerInformation(inventory, s); break;
				case 3: DisplayAllComputersOfABrand(inventory, s); break;
				case 4: DisplayCheaperComputerThan(inventory, s); break;
				case 5: break;
				default: System.out.println("Please enter the number corresponding to your desired option. (1 to 5)");
			}
		}while(chosenOption != 5);
		s.close();
	}
}

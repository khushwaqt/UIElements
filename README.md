# UIElements

# MonthYearPicker Usage


# Gradle
Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.khushwaqt:UIElements:0.0.1'
		}
# Maven


	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
Step 2. Add the dependency

	<dependency>
	    <groupId>com.github.khushwaqt</groupId>
	    <artifactId>UIElements</artifactId>
	    <version>0.0.1</version>
	</dependency>

  
  # Usage
  
  	 Calendar calendar = Calendar.getInstance();
         calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));

        YearMonthPickerDialog yearMonthPickerDialog = new YearMonthPickerDialog(this, calendar,
                new YearMonthPickerDialog.OnDateSetListener() {
                    @Override
                    public void onYearMonthSet(int year, int month, int date) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DATE, date);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                        selectedDate = dateFormat.format(calendar.getTime());
                    }
                });
        yearMonthPickerDialog.setMinYear(calendar.get(Calendar.YEAR) - 1);
        yearMonthPickerDialog.setMaxYear(calendar.get(Calendar.YEAR));
        yearMonthPickerDialog.setMinDate(1);
        yearMonthPickerDialog.setMaxDate(31);
        //type YearMonthPickerDialog.MonthYear;
        //YearMonthPickerDialog.DateMonth;
        //YearMonthPickerDialog.MonthOnly' 
        // type 0 from displaying all mont,year and date
        YearMonthPickerDialog.setCalenderType(type);
        yearMonthPickerDialog.show();
        
# Toast Usage
                CustomToast.ShowDefault(getApplicationContext(), "Default Toast").show();
                CustomToast.ShowToastError(getApplicationContext(), "This is error").show();
                CustomToast.mCustomToast (getApplicationContext(),"Custom",R.color.colorAccent,false,null,false,R.color.white,R.color.red,false);

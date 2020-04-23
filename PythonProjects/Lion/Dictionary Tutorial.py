# A dictionary provides a way to store key-value pairs

# Dictionary is created using name{}
# May use numbers also "1": "January"
monthConversions = {
    "Jan": "January",
    "Feb": "February",
    "Mar": "March",
    "Apr": "April",
    "May": "May",
    "Jun": "June",
    "Jul": "July",
    "Aug": "August",
    "Sept": "September",
    "Oct": "October",
    "Nov": "November",
    "Dec": "December",
}
print()
print("============== Specify a key ===============")
print(monthConversions["Nov"])
print("============== Specify key with .get() ===============")
print(monthConversions.get("Apr"))
print("============== Default Value ===============")
print("Print a default value")
print(monthConversions.get("luv", "Not a valid key"))

print("============== User Input Key ===============")
# User variables converting String input to float
key_value = input("Enter a key: ")
print(monthConversions.get(key_value, "Not an option"))




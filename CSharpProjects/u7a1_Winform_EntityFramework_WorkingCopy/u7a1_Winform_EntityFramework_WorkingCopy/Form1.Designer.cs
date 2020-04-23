namespace u7a1_Winform_EntityFramework_WorkingCopy
{
    partial class CourseFormLoad
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.label1 = new System.Windows.Forms.Label();
            this.userIDTextBox = new System.Windows.Forms.TextBox();
            this.updateDatabaseButton = new System.Windows.Forms.Button();
            this.statusMessageLabel = new System.Windows.Forms.Label();
            this.totalCreditLabel = new System.Windows.Forms.Label();
            this.totalCreditValueLabel = new System.Windows.Forms.Label();
            this.registeredCoursesLabel = new System.Windows.Forms.Label();
            this.registeredCourseList = new System.Windows.Forms.ListView();
            this.selectCoursePromptLabel = new System.Windows.Forms.Label();
            this.courseComboBox = new System.Windows.Forms.ComboBox();
            this.SuspendLayout();
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Microsoft Sans Serif", 10.8F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label1.Location = new System.Drawing.Point(71, 83);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(76, 24);
            this.label1.TabIndex = 19;
            this.label1.Text = "User ID:";
            // 
            // userIDTextBox
            // 
            this.userIDTextBox.Location = new System.Drawing.Point(75, 110);
            this.userIDTextBox.Name = "userIDTextBox";
            this.userIDTextBox.Size = new System.Drawing.Size(306, 22);
            this.userIDTextBox.TabIndex = 18;
            // 
            // updateDatabaseButton
            // 
            this.updateDatabaseButton.Location = new System.Drawing.Point(751, 386);
            this.updateDatabaseButton.Margin = new System.Windows.Forms.Padding(4);
            this.updateDatabaseButton.Name = "updateDatabaseButton";
            this.updateDatabaseButton.Size = new System.Drawing.Size(171, 28);
            this.updateDatabaseButton.TabIndex = 17;
            this.updateDatabaseButton.Text = "Update Database";
            this.updateDatabaseButton.UseVisualStyleBackColor = true;
            this.updateDatabaseButton.Click += new System.EventHandler(this.updateDatabaseButton_Click);
            // 
            // statusMessageLabel
            // 
            this.statusMessageLabel.AutoSize = true;
            this.statusMessageLabel.Font = new System.Drawing.Font("Times New Roman", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.statusMessageLabel.Location = new System.Drawing.Point(72, 312);
            this.statusMessageLabel.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.statusMessageLabel.Name = "statusMessageLabel";
            this.statusMessageLabel.Size = new System.Drawing.Size(260, 22);
            this.statusMessageLabel.TabIndex = 16;
            this.statusMessageLabel.Text = "Select a course from list above.";
            // 
            // totalCreditLabel
            // 
            this.totalCreditLabel.AutoSize = true;
            this.totalCreditLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 10.8F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.totalCreditLabel.Location = new System.Drawing.Point(738, 306);
            this.totalCreditLabel.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.totalCreditLabel.Name = "totalCreditLabel";
            this.totalCreditLabel.Size = new System.Drawing.Size(119, 24);
            this.totalCreditLabel.TabIndex = 15;
            this.totalCreditLabel.Text = "Total Credits:";
            // 
            // totalCreditValueLabel
            // 
            this.totalCreditValueLabel.AutoSize = true;
            this.totalCreditValueLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 10.8F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.totalCreditValueLabel.Location = new System.Drawing.Point(902, 306);
            this.totalCreditValueLabel.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.totalCreditValueLabel.Name = "totalCreditValueLabel";
            this.totalCreditValueLabel.Size = new System.Drawing.Size(20, 24);
            this.totalCreditValueLabel.TabIndex = 14;
            this.totalCreditValueLabel.Text = "0";
            // 
            // registeredCoursesLabel
            // 
            this.registeredCoursesLabel.AutoSize = true;
            this.registeredCoursesLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 10.8F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.registeredCoursesLabel.Location = new System.Drawing.Point(615, 42);
            this.registeredCoursesLabel.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.registeredCoursesLabel.Name = "registeredCoursesLabel";
            this.registeredCoursesLabel.Size = new System.Drawing.Size(186, 24);
            this.registeredCoursesLabel.TabIndex = 13;
            this.registeredCoursesLabel.Text = "Registered Courses: ";
            // 
            // registeredCourseList
            // 
            this.registeredCourseList.Location = new System.Drawing.Point(619, 83);
            this.registeredCourseList.Margin = new System.Windows.Forms.Padding(4);
            this.registeredCourseList.Name = "registeredCourseList";
            this.registeredCourseList.Size = new System.Drawing.Size(494, 118);
            this.registeredCourseList.TabIndex = 12;
            this.registeredCourseList.UseCompatibleStateImageBehavior = false;
            // 
            // selectCoursePromptLabel
            // 
            this.selectCoursePromptLabel.AutoSize = true;
            this.selectCoursePromptLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 10.8F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.selectCoursePromptLabel.Location = new System.Drawing.Point(71, 177);
            this.selectCoursePromptLabel.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.selectCoursePromptLabel.Name = "selectCoursePromptLabel";
            this.selectCoursePromptLabel.Size = new System.Drawing.Size(138, 24);
            this.selectCoursePromptLabel.TabIndex = 11;
            this.selectCoursePromptLabel.Text = "Select Course: ";
            // 
            // courseComboBox
            // 
            this.courseComboBox.FormattingEnabled = true;
            this.courseComboBox.Location = new System.Drawing.Point(75, 220);
            this.courseComboBox.Margin = new System.Windows.Forms.Padding(4);
            this.courseComboBox.Name = "courseComboBox";
            this.courseComboBox.Size = new System.Drawing.Size(486, 24);
            this.courseComboBox.TabIndex = 10;
            this.courseComboBox.SelectedIndexChanged += new System.EventHandler(this.courseComboBox_SelectedIndexChanged);
            // 
            // CourseFormLoad
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1170, 450);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.userIDTextBox);
            this.Controls.Add(this.updateDatabaseButton);
            this.Controls.Add(this.statusMessageLabel);
            this.Controls.Add(this.totalCreditLabel);
            this.Controls.Add(this.totalCreditValueLabel);
            this.Controls.Add(this.registeredCoursesLabel);
            this.Controls.Add(this.registeredCourseList);
            this.Controls.Add(this.selectCoursePromptLabel);
            this.Controls.Add(this.courseComboBox);
            this.Name = "CourseFormLoad";
            this.Text = "EF Course Registration";
            this.Load += new System.EventHandler(this.CourseFormLoad_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.TextBox userIDTextBox;
        private System.Windows.Forms.Button updateDatabaseButton;
        private System.Windows.Forms.Label statusMessageLabel;
        private System.Windows.Forms.Label totalCreditLabel;
        private System.Windows.Forms.Label totalCreditValueLabel;
        private System.Windows.Forms.Label registeredCoursesLabel;
        private System.Windows.Forms.ListView registeredCourseList;
        private System.Windows.Forms.Label selectCoursePromptLabel;
        private System.Windows.Forms.ComboBox courseComboBox;
    }
}


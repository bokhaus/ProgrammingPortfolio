namespace Unit6_Registration_WinForm_SQL
{
    partial class Form1
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
            this.courseComboBox = new System.Windows.Forms.ComboBox();
            this.selectCoursePromptLabel = new System.Windows.Forms.Label();
            this.registeredCourseList = new System.Windows.Forms.ListView();
            this.registeredCoursesLabel = new System.Windows.Forms.Label();
            this.totalCreditValueLabel = new System.Windows.Forms.Label();
            this.totalCreditLabel = new System.Windows.Forms.Label();
            this.statusMessageLabel = new System.Windows.Forms.Label();
            this.updateDatabaseButton = new System.Windows.Forms.Button();
            this.userIDTextBox = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // courseComboBox
            // 
            this.courseComboBox.FormattingEnabled = true;
            this.courseComboBox.Location = new System.Drawing.Point(199, 109);
            this.courseComboBox.Margin = new System.Windows.Forms.Padding(4);
            this.courseComboBox.Name = "courseComboBox";
            this.courseComboBox.Size = new System.Drawing.Size(160, 24);
            this.courseComboBox.TabIndex = 0;
            this.courseComboBox.SelectedIndexChanged += new System.EventHandler(this.courseComboBox_SelectedIndexChanged);
            // 
            // selectCoursePromptLabel
            // 
            this.selectCoursePromptLabel.AutoSize = true;
            this.selectCoursePromptLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 10.8F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.selectCoursePromptLabel.Location = new System.Drawing.Point(35, 109);
            this.selectCoursePromptLabel.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.selectCoursePromptLabel.Name = "selectCoursePromptLabel";
            this.selectCoursePromptLabel.Size = new System.Drawing.Size(138, 24);
            this.selectCoursePromptLabel.TabIndex = 1;
            this.selectCoursePromptLabel.Text = "Select Course: ";
            // 
            // registeredCourseList
            // 
            this.registeredCourseList.Location = new System.Drawing.Point(597, 15);
            this.registeredCourseList.Margin = new System.Windows.Forms.Padding(4);
            this.registeredCourseList.Name = "registeredCourseList";
            this.registeredCourseList.Size = new System.Drawing.Size(494, 118);
            this.registeredCourseList.TabIndex = 2;
            this.registeredCourseList.UseCompatibleStateImageBehavior = false;
            // 
            // registeredCoursesLabel
            // 
            this.registeredCoursesLabel.AutoSize = true;
            this.registeredCoursesLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 10.8F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.registeredCoursesLabel.Location = new System.Drawing.Point(392, 37);
            this.registeredCoursesLabel.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.registeredCoursesLabel.Name = "registeredCoursesLabel";
            this.registeredCoursesLabel.Size = new System.Drawing.Size(186, 24);
            this.registeredCoursesLabel.TabIndex = 3;
            this.registeredCoursesLabel.Text = "Registered Courses: ";
            // 
            // totalCreditValueLabel
            // 
            this.totalCreditValueLabel.AutoSize = true;
            this.totalCreditValueLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 10.8F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.totalCreditValueLabel.Location = new System.Drawing.Point(880, 191);
            this.totalCreditValueLabel.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.totalCreditValueLabel.Name = "totalCreditValueLabel";
            this.totalCreditValueLabel.Size = new System.Drawing.Size(20, 24);
            this.totalCreditValueLabel.TabIndex = 4;
            this.totalCreditValueLabel.Text = "0";
            // 
            // totalCreditLabel
            // 
            this.totalCreditLabel.AutoSize = true;
            this.totalCreditLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 10.8F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.totalCreditLabel.Location = new System.Drawing.Point(753, 191);
            this.totalCreditLabel.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.totalCreditLabel.Name = "totalCreditLabel";
            this.totalCreditLabel.Size = new System.Drawing.Size(119, 24);
            this.totalCreditLabel.TabIndex = 5;
            this.totalCreditLabel.Text = "Total Credits:";
            // 
            // statusMessageLabel
            // 
            this.statusMessageLabel.AutoSize = true;
            this.statusMessageLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.statusMessageLabel.Location = new System.Drawing.Point(36, 272);
            this.statusMessageLabel.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.statusMessageLabel.Name = "statusMessageLabel";
            this.statusMessageLabel.Size = new System.Drawing.Size(216, 18);
            this.statusMessageLabel.TabIndex = 6;
            this.statusMessageLabel.Text = "Select a course from list above.";
            // 
            // updateDatabaseButton
            // 
            this.updateDatabaseButton.Location = new System.Drawing.Point(729, 272);
            this.updateDatabaseButton.Margin = new System.Windows.Forms.Padding(4);
            this.updateDatabaseButton.Name = "updateDatabaseButton";
            this.updateDatabaseButton.Size = new System.Drawing.Size(171, 28);
            this.updateDatabaseButton.TabIndex = 7;
            this.updateDatabaseButton.Text = "Update Database";
            this.updateDatabaseButton.UseVisualStyleBackColor = true;
            this.updateDatabaseButton.Click += new System.EventHandler(this.updateDatabaseButton_Click);
            // 
            // userIDTextBox
            // 
            this.userIDTextBox.Location = new System.Drawing.Point(141, 37);
            this.userIDTextBox.Name = "userIDTextBox";
            this.userIDTextBox.Size = new System.Drawing.Size(218, 22);
            this.userIDTextBox.TabIndex = 8;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Microsoft Sans Serif", 10.8F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label1.Location = new System.Drawing.Point(36, 40);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(76, 24);
            this.label1.TabIndex = 9;
            this.label1.Text = "User ID:";
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1126, 380);
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
            this.Margin = new System.Windows.Forms.Padding(4);
            this.Name = "Form1";
            this.Text = "IT Course Registration";
            this.Load += new System.EventHandler(this.Form1_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ComboBox courseComboBox;
        private System.Windows.Forms.Label selectCoursePromptLabel;
        private System.Windows.Forms.ListView registeredCourseList;
        private System.Windows.Forms.Label registeredCoursesLabel;
        private System.Windows.Forms.Label totalCreditValueLabel;
        private System.Windows.Forms.Label totalCreditLabel;
        private System.Windows.Forms.Label statusMessageLabel;
        private System.Windows.Forms.Button updateDatabaseButton;
        private System.Windows.Forms.TextBox userIDTextBox;
        private System.Windows.Forms.Label label1;
    }
}

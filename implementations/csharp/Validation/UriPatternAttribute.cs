﻿/* 
 * Copyright (c) 2014, Furore (info@furore.com) and contributors
 * See the file CONTRIBUTORS for details.
 * 
 * This file is licensed under the BSD 3-Clause license
 * available at https://raw.githubusercontent.com/ewoutkramer/fhir-net-api/master/LICENSE
 */

using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;

namespace Hl7.Fhir.Validation
{
    [AttributeUsage(AttributeTargets.Property, Inherited = false, AllowMultiple = false)]
    public class UriPatternAttribute : ValidationAttribute
    {
        public static bool IsValidValue(string value)
        {
            var uri = new Uri((string)value);

            if (uri.IsAbsoluteUri)
            {
                var uris = uri.ToString();

                if (uris.StartsWith("urn:oid:") && !OidPatternAttribute.IsValidValue(uris))
                    return false;
                else if (uris.StartsWith("urn:uuid:") && !UuidPatternAttribute.IsValidValue(uris))
                    return false;
            }

            return true;
        }

        protected override ValidationResult IsValid(object value, ValidationContext validationContext)
        {
            if (value == null) return ValidationResult.Success;

            if (value.GetType() != typeof(string))
                throw new ArgumentException("UriPatternAttribute can only be applied to .NET Uri properties");

            if (!IsValidValue(value as string))
                return FhirValidator.BuildResult(validationContext, "Uri uses an urn:oid or urn:uuid scheme, but the syntax {0} is incorrect", value as string);
            else
                return ValidationResult.Success;
        }
    }
}
